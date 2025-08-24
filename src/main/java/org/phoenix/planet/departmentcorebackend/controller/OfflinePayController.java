package org.phoenix.planet.departmentcorebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.phoenix.planet.departmentcorebackend.dto.offline.request.OfflinePayload;
import org.phoenix.planet.departmentcorebackend.service.offline.OfflinePayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offline-pays")
public class OfflinePayController {

    private final OfflinePayService offlinePayService;

    @PostMapping
    public ResponseEntity<?> createOfflinePay(
        @RequestBody @Valid OfflinePayload offlinePayload
    ) {

        offlinePayService.save(offlinePayload);
        return ResponseEntity.ok().build();
    }
}
