package org.phoenix.planet.departmentcorebackend.service.offline;


import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePayHistory;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePaySaveRequest;

public interface OfflinePayHistoryService {

    long save(OfflinePaySaveRequest offlinePaySaveRequest);

    OfflinePayHistory searchByBarcode(String code);

    void updateStockIssueStatusTrue(long offlinePayHistoryId);
}
