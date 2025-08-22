package org.phoenix.planet.departmentcorebackend.event;

import lombok.Builder;

@Builder
public record PayEvent(
    String eventName,
    long cardCompanyId,
    String cardNumber
) {

}