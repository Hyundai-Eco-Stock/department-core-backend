package org.phoenix.planet.departmentcorebackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.phoenix.planet.departmentcorebackend.dto.eco_stock.raw.EcoStock;

@Mapper
public interface EcoStockMapper {

    EcoStock selectById(Long id);
}
