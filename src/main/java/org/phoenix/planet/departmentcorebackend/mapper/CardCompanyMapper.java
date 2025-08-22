package org.phoenix.planet.departmentcorebackend.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.phoenix.planet.departmentcorebackend.dto.card.response.CardCompanyListResponse;

@Mapper
public interface CardCompanyMapper {

    List<CardCompanyListResponse> selectAll();
}
