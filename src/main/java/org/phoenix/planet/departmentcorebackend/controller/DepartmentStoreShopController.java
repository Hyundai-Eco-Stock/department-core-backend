package org.phoenix.planet.departmentcorebackend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.phoenix.planet.departmentcorebackend.dto.offline.response.OfflineProductListResponse;
import org.phoenix.planet.departmentcorebackend.dto.offline.response.OfflineShopListResponse;
import org.phoenix.planet.departmentcorebackend.service.offline.OfflineProductService;
import org.phoenix.planet.departmentcorebackend.service.offline.OfflineShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/department-stores/shops")
public class DepartmentStoreShopController {

    private final OfflineShopService offlineShopService;
    private final OfflineProductService offlineProductService;

    /**
     * 가상의 포스기에서 사용
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OfflineShopListResponse>> searchAllDepartmentStoreShops() {

        List<OfflineShopListResponse> shopList = offlineShopService.searchAll();
        return ResponseEntity.ok(shopList);
    }

    /**
     * 가상의 포스기에서 사용
     *
     * @param shopId
     * @return
     */
    @GetMapping("/{shop-id}/products")
    public ResponseEntity<List<OfflineProductListResponse>> searchAllDepartmentStoreShopProducts(
        @PathVariable("shop-id") long shopId
    ) {

        List<OfflineProductListResponse> shopList = offlineProductService.searchAllByShopId(shopId);
        return ResponseEntity.ok(shopList);
    }
}
