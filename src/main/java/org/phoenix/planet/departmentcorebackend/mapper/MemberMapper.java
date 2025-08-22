package org.phoenix.planet.departmentcorebackend.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.phoenix.planet.departmentcorebackend.constant.Sex;
import org.phoenix.planet.departmentcorebackend.dto.member.raw.Member;
import org.phoenix.planet.departmentcorebackend.dto.member.response.MemberListResponse;

@Mapper
public interface MemberMapper {

    Optional<Member> findById(@Param("memberId") long memberId);

    Optional<Member> findByEmail(@Param("email") String email);

    List<MemberListResponse> findAll();

    void insert(Member member);

    void updateProfileUrl(
        @Param("memberId") long memberId,
        @Param("profileUrl") String profileUrl
    );

    void update(
        @Param("memberId") long memberId,
        @Param("pwdHash") String pwdHash,
        @Param("sex") Sex sex,
        @Param("birth") String birth,
        @Param("address") String address,
        @Param("detailAddress") String detailAddress);
}
