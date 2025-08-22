package org.phoenix.planet.departmentcorebackend.dto.member.response;

import org.phoenix.planet.departmentcorebackend.constant.Sex;


public record MemberListResponse(
    long id,
    String email,
    String name,
    Sex sex
) {

}
