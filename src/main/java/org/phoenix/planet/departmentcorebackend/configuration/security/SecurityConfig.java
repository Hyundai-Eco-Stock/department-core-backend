package org.phoenix.planet.departmentcorebackend.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;
    private final TokenExceptionFilter tokenExceptionFilter;

    public SecurityConfig(
        @Lazy TokenAuthenticationFilter tokenAuthenticationFilter,
        CorsConfigurationSource corsConfigurationSource,
        @Lazy TokenExceptionFilter tokenExceptionFilter) {

        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.corsConfigurationSource = corsConfigurationSource;
        this.tokenExceptionFilter = tokenExceptionFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // csrf 비활성화 (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
            .csrf(AbstractHttpConfigurer::disable)
            // cors 활성화
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            // 기본 인증 로그인 비활성화
            .httpBasic(AbstractHttpConfigurer::disable)
            // 기본 login form 비활성화
            .formLogin(AbstractHttpConfigurer::disable)
            // 기본 logout 비활성화
            .logout(AbstractHttpConfigurer::disable)
            // X-Frame-Options 비활성화
            .headers(c -> c.frameOptions(
                FrameOptionsConfig::disable))
            // 세션 사용하지 않음
            .sessionManagement(c ->
                c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // request 인증, 인가 설정
            .authorizeHttpRequests(request ->
                request
                    .requestMatchers(
                        new AntPathRequestMatcher("/error"),
                        new AntPathRequestMatcher("/favicon.ico"),
                        new AntPathRequestMatcher("/swagger-ui/**"),
                        new AntPathRequestMatcher("/v3/api-docs/**"),
                        new AntPathRequestMatcher("/health"),
                        new AntPathRequestMatcher("/")
                    ).permitAll()
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .anyRequest().authenticated()
            )

            // JWT 관련 설정
            .addFilterBefore(tokenExceptionFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(tokenAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class)

            // 인증 예외 핸들링
            .exceptionHandling((exceptions) -> exceptions
                .accessDeniedHandler(new AccessDeniedHandlerImpl()) // 403
            );

        return http.build();
    }
}