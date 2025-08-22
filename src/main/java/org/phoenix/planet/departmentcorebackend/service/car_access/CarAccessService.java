package org.phoenix.planet.departmentcorebackend.service.car_access;

import java.util.List;
import org.phoenix.planet.departmentcorebackend.dto.car_access.request.CarEnterRequest;
import org.phoenix.planet.departmentcorebackend.dto.car_access.request.CarExitRequest;
import org.phoenix.planet.departmentcorebackend.dto.car_access.response.CarAccessHistoryResponse;

public interface CarAccessService {

    void processEnterCar(CarEnterRequest carEnterRequest);

    void processExitCar(CarExitRequest carExitRequest);

    List<CarAccessHistoryResponse> searchCarAccessHistories();
}
