package com.apple.shop.member.controller;

import com.apple.shop.member.service.MemberService;
import com.apple.shop.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public Member writePost(@RequestBody Member member) {
        return memberService.join(member);
    }

    //로그인
    @PostMapping("/login")
    public Member login(@RequestBody Member member) {
        return memberService.login(member.getUsername(), member.getPassword());
    }

    // 로그인 유저 정보
    @PostMapping("/getCurrentMember")
    public Member getCurrentMember(Authentication authentication) {
        return memberService.getCurrentMember(authentication);
    }
}