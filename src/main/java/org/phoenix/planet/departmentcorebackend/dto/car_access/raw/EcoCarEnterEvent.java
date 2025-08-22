package org.phoenix.planet.departmentcorebackend.dto.car_access.raw;

import lombok.Builder;

@Builder
public record EcoCarEnterEvent(
    String carNumber
) {

}
