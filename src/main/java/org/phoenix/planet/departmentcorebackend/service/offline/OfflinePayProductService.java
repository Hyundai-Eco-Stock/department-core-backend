package org.phoenix.planet.departmentcorebackend.service.offline;

import java.util.List;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePayProduct;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePayProductSaveRequest;

public interface OfflinePayProductService {

    void save(OfflinePayProductSaveRequest build);

    List<OfflinePayProduct> searchByPayHistoryId(long offlinePayHistoryId);
}
