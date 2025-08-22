package org.phoenix.planet.departmentcorebackend.service.offline;

import java.util.List;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflineProduct;
import org.phoenix.planet.departmentcorebackend.dto.offline.response.OfflineProductListResponse;

public interface OfflineProductService {

    List<OfflineProductListResponse> searchAllByShopId(long shopId);

    long getTotalPriceByIds(List<Long> productIdList);

    OfflineProduct searchById(Long productId);

    List<Long> searchTumblerProductIdList();

    List<Long> searchPaperBagProductIdList();

    List<OfflineProduct> searchByIds(List<Long> productIds);
}
