package org.phoenix.planet.departmentcorebackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.phoenix.planet.departmentcorebackend.constant.CarEcoType;
import org.phoenix.planet.departmentcorebackend.dto.car.response.MemberCarResponse;

@Mapper
public interface MemberCarMapper {

    MemberCarResponse selectByMemberId(
        @Param("memberId") long memberId);

    MemberCarResponse selectByCarNumber(
        @Param("carNumber") String carNumber);

    void insert(
        @Param("memberId") long memberId,
        @Param("carNumber") String carNumber,
        @Param("carEcoType") CarEcoType carEcoType);


}
