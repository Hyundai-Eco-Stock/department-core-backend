package org.phoenix.planet.departmentcorebackend.service.receipt;


import org.phoenix.planet.departmentcorebackend.dto.offline.request.OfflinePayload;

public interface OfflinePayService {

    void save(OfflinePayload offlinePayload);
}
