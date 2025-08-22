package org.phoenix.planet.departmentcorebackend.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflineProduct;
import org.phoenix.planet.departmentcorebackend.dto.offline.response.OfflineProductListResponse;

@Mapper
public interface OfflineProductMapper {

    List<OfflineProductListResponse> selectAllByOfflineShopId(long offlineShopId);

    long selectSumPriceOfIds(List<Long> productIdList);

    Optional<OfflineProduct> selectById(Long itemId);

    List<OfflineProduct> selectByIds(List<Long> productIds);

    List<Long> selectTumblerProductIdList();

    List<Long> selectPaperBagProductIdList();
}
