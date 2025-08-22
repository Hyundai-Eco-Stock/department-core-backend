package org.phoenix.planet.departmentcorebackend.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.phoenix.planet.departmentcorebackend.dto.car_access.response.CarAccessHistoryResponse;

@Mapper
public interface CarAccessMapper {

    void insertEnterCar(@Param("carNumber") String carNumber);

    void insertExitCar(@Param("carNumber") String carNumber);

    List<CarAccessHistoryResponse> selectCarAccessHistories();

}
