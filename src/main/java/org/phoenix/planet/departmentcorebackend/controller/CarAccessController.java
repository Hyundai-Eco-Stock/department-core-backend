package org.phoenix.planet.departmentcorebackend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.phoenix.planet.departmentcorebackend.dto.car_access.request.CarEnterRequest;
import org.phoenix.planet.departmentcorebackend.dto.car_access.request.CarExitRequest;
import org.phoenix.planet.departmentcorebackend.dto.car_access.response.CarAccessHistoryResponse;
import org.phoenix.planet.departmentcorebackend.service.car_access.CarAccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cars/access")
@RequiredArgsConstructor
public class CarAccessController {

    private final CarAccessService carAccessService;

    @GetMapping
    public ResponseEntity<List<CarAccessHistoryResponse>> carHistory(
    ) {
        log.info("차량 입출차 기록 조회");
        List<CarAccessHistoryResponse> histories = carAccessService.searchCarAccessHistories();
        return ResponseEntity.ok(histories);
    }

    @PostMapping("/enter")
    public ResponseEntity<Void> carEnter(
        @RequestBody CarEnterRequest carEnterRequest
    ) {
        // 입차 처리
        log.info("차량 입차: {}", carEnterRequest.carNumber());
        carAccessService.processEnterCar(carEnterRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/exit")
    public ResponseEntity<Void> carExit(
        @RequestBody CarExitRequest carExitRequest
    ) {
        // 출차 처리
        log.info("차량 출차: {}", carExitRequest.carNumber());
        carAccessService.processExitCar(carExitRequest);
        return ResponseEntity.ok().build();
    }
}