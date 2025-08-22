package org.phoenix.planet.departmentcorebackend.service.offline;

import java.util.List;
import org.phoenix.planet.departmentcorebackend.dto.offline.response.OfflineShopListResponse;

public interface OfflineShopService {

    List<OfflineShopListResponse> searchAll();

    String searchTypeById(Long shopId);
}
