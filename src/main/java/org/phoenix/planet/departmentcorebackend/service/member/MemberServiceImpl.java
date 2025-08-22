package org.phoenix.planet.departmentcorebackend.service.member;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.phoenix.planet.departmentcorebackend.dto.member.response.MemberListResponse;
import org.phoenix.planet.departmentcorebackend.mapper.MemberMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    @Override
    public List<MemberListResponse> searchAllMembers() {

        return memberMapper.findAll();
    }
}
