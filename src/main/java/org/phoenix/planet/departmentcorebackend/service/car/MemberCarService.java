package org.phoenix.planet.departmentcorebackend.service.car;


import org.phoenix.planet.departmentcorebackend.dto.car.response.MemberCarResponse;

public interface MemberCarService {

    MemberCarResponse searchByMemberId(long memberId);

    MemberCarResponse searchByCarNumber(String carNumber);

}
