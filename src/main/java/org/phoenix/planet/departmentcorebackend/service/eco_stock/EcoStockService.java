package org.phoenix.planet.departmentcorebackend.service.eco_stock;


import org.phoenix.planet.departmentcorebackend.dto.eco_stock.raw.EcoStock;

public interface EcoStockService {

    EcoStock searchById(long ecoStockId);
}
