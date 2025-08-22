package org.phoenix.planet.departmentcorebackend.dto.eco_stock.raw;

public record EcoStock(
    Long id,
    String name,
    Long quantity,
    String imageUrl
) {

}
