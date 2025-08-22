package org.phoenix.planet.departmentcorebackend.event;

import lombok.Builder;

@Builder
public record EcoCarEnterEvent(
    String carNumber
) {

}
