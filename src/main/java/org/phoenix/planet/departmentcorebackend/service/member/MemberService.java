package org.phoenix.planet.departmentcorebackend.service.member;

import java.util.List;
import org.phoenix.planet.departmentcorebackend.dto.member.response.MemberListResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

    List<MemberListResponse> searchAllMembers();
}
