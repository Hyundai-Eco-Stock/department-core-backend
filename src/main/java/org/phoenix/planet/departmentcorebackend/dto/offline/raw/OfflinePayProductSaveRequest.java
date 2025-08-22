package org.phoenix.planet.departmentcorebackend.dto.offline.raw;

import lombok.Builder;

@Builder
public record OfflinePayProductSaveRequest(
    long offlinePayHistoryId,
    long productId,
    String name,
    long price,
    int amount
) {

}
