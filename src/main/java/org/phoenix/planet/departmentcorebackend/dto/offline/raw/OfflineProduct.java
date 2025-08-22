package org.phoenix.planet.departmentcorebackend.dto.offline.raw;

public record OfflineProduct(
    long offlineProductId,
    long offlineShopId,
    String name,
    long price
) {

}
