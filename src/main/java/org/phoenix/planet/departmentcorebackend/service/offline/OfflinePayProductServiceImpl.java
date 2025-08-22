package org.phoenix.planet.departmentcorebackend.service.offline;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePayProduct;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePayProductSaveRequest;
import org.phoenix.planet.departmentcorebackend.mapper.OfflinePayProductMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfflinePayProductServiceImpl implements OfflinePayProductService {

    private final OfflinePayProductMapper offlinePayProductMapper;

    @Override
    public void save(OfflinePayProductSaveRequest offlinePayProductSaveRequest) {

        offlinePayProductMapper.insert(offlinePayProductSaveRequest);
    }

    @Override
    public List<OfflinePayProduct> searchByPayHistoryId(long offlinePayHistoryId) {

        return offlinePayProductMapper.selectByPayHistoryId(offlinePayHistoryId);
    }
}
