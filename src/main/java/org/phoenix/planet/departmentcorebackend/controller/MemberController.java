package org.phoenix.planet.departmentcorebackend.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.phoenix.planet.departmentcorebackend.dto.member.response.MemberListResponse;
import org.phoenix.planet.departmentcorebackend.service.car.MemberCarService;
import org.phoenix.planet.departmentcorebackend.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberCarService memberCarService;

    @GetMapping
    public ResponseEntity<List<MemberListResponse>> searchAllMembers() {

        List<MemberListResponse> memberList = memberService.searchAllMembers();
        return ResponseEntity.ok(memberList);
    }


}
