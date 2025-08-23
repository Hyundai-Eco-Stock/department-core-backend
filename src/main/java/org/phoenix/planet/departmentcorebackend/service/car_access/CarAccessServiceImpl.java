package org.phoenix.planet.departmentcorebackend.service.car_access;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.phoenix.planet.departmentcorebackend.constant.CarEcoType;
import org.phoenix.planet.departmentcorebackend.dto.car_access.request.CarEnterRequest;
import org.phoenix.planet.departmentcorebackend.dto.car_access.request.CarExitRequest;
import org.phoenix.planet.departmentcorebackend.dto.car_access.response.CarAccessHistoryResponse;
import org.phoenix.planet.departmentcorebackend.event.EcoCarEnterEvent;
import org.phoenix.planet.departmentcorebackend.mapper.CarAccessMapper;
import org.phoenix.planet.departmentcorebackend.producer.OfflineEventProducer;
import org.phoenix.planet.departmentcorebackend.service.car.CarTypeSearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarAccessServiceImpl implements CarAccessService {

    private final CarAccessMapper carAccessMapper;
    private final OfflineEventProducer offlineEventProducer;
    private final CarTypeSearchService carTypeSearchService;

    @Override
    @Transactional
    public void processEnterCar(CarEnterRequest carEnterRequest) {
        // DB에 저장
        carAccessMapper.insertEnterCar(carEnterRequest.carNumber());
        CarEcoType carEcoType = carTypeSearchService.getTypeByCarNumber(
            carEnterRequest.carNumber());
        // 친환경 차면 이벤트 발행
        if (CarEcoType.ELECTRONIC.equals(carEcoType) || CarEcoType.HYBRID.equals(carEcoType)) {
            offlineEventProducer.publishEcoCarEnterEvent(
                EcoCarEnterEvent.builder()
                    .carNumber(carEnterRequest.carNumber())
                    .build());
        }
    }

    @Override
    public void processExitCar(CarExitRequest carExitRequest) {
        // DB에 저장
        carAccessMapper.insertExitCar(carExitRequest.carNumber());

    }

    @Override
    public List<CarAccessHistoryResponse> searchCarAccessHistories() {

        return carAccessMapper.selectCarAccessHistories();
    }
}
