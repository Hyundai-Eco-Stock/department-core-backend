package org.phoenix.planet.departmentcorebackend.dto.offline.raw;

public record OfflinePayHistory(
    long offlinePayHistoryId,
    long shopId,
    long cardCompanyId,
    int cardNumberLast4,
    long totalPrice,
    String barcode,
    boolean stockIssued
) {

}
