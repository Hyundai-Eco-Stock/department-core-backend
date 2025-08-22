package org.phoenix.planet.departmentcorebackend.dto.offline.response;

public record OfflineProductListResponse(
    Long productId,
    Long shopId,
    String name,
    int price
) {

}
