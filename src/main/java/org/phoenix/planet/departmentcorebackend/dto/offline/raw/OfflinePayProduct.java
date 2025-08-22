package org.phoenix.planet.departmentcorebackend.dto.offline.raw;

public record OfflinePayProduct(
    long offlinePayProductId,
    long offlinePayHistoryId,
    long productId,
    String name,
    long price,
    int amount
) {

}