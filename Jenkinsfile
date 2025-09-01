pipeline {
  agent {
    docker {
      image 'cimg/openjdk:21.0'
      args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
    }
  }

  options {
    timestamps()
    timeout(time: 20, unit: 'MINUTES')
  }

  environment {
    AWS_REGION = 'ap-northeast-2'
    IMAGE_TAG = 'latest'
    CONTAINER_NAME = 'department_store'
    ECR_REPO = '958948421852.dkr.ecr.ap-northeast-2.amazonaws.com/department_store'
  }

  stages {
    stage('Check Conditions') {
      steps {
        script {
          if (env.GIT_BRANCH != 'origin/main') {
            error("[SKIP] Not deploy branch → stopping pipeline.")
          }
          if (env.CHANGE_ID != null) {
            error("[SKIP] This is a PR build (not merged) → stopping pipeline.")
          }
          echo "[INFO] ✅ Valid deploy pipeline (deploy branch push or PR merge). Continuing..."
        }
      }
    }

    stage('Setup') {
      steps {
        script {
          def awsInstalled = sh(
            script: 'which aws || echo "notfound"',
            returnStdout: true
          ).trim()
          if (awsInstalled.contains('notfound')) {
            echo "[INFO] Installing required packages..."
            sh '''
              apt-get update -qq
              apt-get install -y curl unzip python3 python3-pip awscli
            '''
          } else {
            echo "[INFO] AWS CLI already available, skipping package installation..."
          }
        }
        sh '''
          echo "[INFO] Tool versions:"
          java -version
          which docker && docker --version || echo "Docker not available"
          aws --version
        '''
      }
    }

    stage('Build') {
      steps {
        sh '''
          chmod +x ./gradlew
          ./gradlew build -x test --no-daemon --build-cache
          ls -la build/libs/
        '''
      }
    }

    stage('Docker Build & Push') {
      steps {
        withCredentials([
          string(credentialsId: 'AWS_ACCESS_KEY', variable: 'AWS_ACCESS_KEY_ID'),
          string(credentialsId: 'AWS_SECRET_KEY', variable: 'AWS_SECRET_ACCESS_KEY')
        ]) {
          sh '''
            aws configure set aws_access_key_id "$AWS_ACCESS_KEY_ID"
            aws configure set aws_secret_access_key "$AWS_SECRET_ACCESS_KEY"
            aws configure set default.region "ap-northeast-2"

            aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin $ECR_REPO

            docker build -t $ECR_REPO:$IMAGE_TAG .
            docker push $ECR_REPO:$IMAGE_TAG
          '''
        }
      }
    }

    stage('Deploy to EC2') {
      steps {
        withCredentials([
          string(credentialsId: 'AWS_ACCESS_KEY', variable: 'AWS_ACCESS_KEY_ID'),
          string(credentialsId: 'AWS_SECRET_KEY', variable: 'AWS_SECRET_ACCESS_KEY')
        ]) {
          sh '''
            INSTANCE_IDS=$(aws ec2 describe-instances \
              --filters "Name=tag:Deployment,Values=department_store" "Name=instance-state-name,Values=running" \
              --query "Reservations[].Instances[].InstanceId" --output text)

            if [ -z "$INSTANCE_IDS" ]; then
              echo "[INFO] No running instances found with tag Deployment=department_store"
              exit 0
            fi

            aws ssm send-command \
              --document-name "AWS-RunShellScript" \
              --comment "Deploy Docker container" \
              --instance-ids $INSTANCE_IDS \
              --parameters commands='[
                "aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin $ECR_REPO",
                "docker pull $ECR_REPO:$IMAGE_TAG",
                "docker rm -f $CONTAINER_NAME || true",
                "docker run -d --name $CONTAINER_NAME -p 8080:8080 --restart unless-stopped --env SPRING_PROFILES_ACTIVE=prod --env-file /home/ec2-user/.env $ECR_REPO:$IMAGE_TAG"
              ]' \
              --region ap-northeast-2
          '''
        }
      }
    }
  }

  post {
    success {
      echo "🎉 Backend deployment completed successfully!"
    }
    failure {
      echo "❌ Backend deployment failed!"
    }
    cleanup {
      sh 'docker system prune -f || true'
    }
  }
}

