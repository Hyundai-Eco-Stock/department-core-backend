package org.phoenix.planet.departmentcorebackend.service.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.phoenix.planet.departmentcorebackend.dto.car.response.MemberCarResponse;
import org.phoenix.planet.departmentcorebackend.mapper.MemberCarMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberMemberCarServiceImpl implements MemberCarService {

    private final MemberCarMapper memberCarMapper;


    @Override
    public MemberCarResponse searchByMemberId(long memberId) {

        return memberCarMapper.selectByMemberId(memberId);
    }

    @Override
    public MemberCarResponse searchByCarNumber(String carNumber) {

        return memberCarMapper.selectByCarNumber(carNumber);
    }
}
