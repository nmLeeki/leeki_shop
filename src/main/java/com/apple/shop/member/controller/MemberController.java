package com.apple.shop.member.controller;

import com.apple.shop.member.service.MemberService;
import com.apple.shop.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        // 인증 성공 후 사용자 정보 반환
        return memberService.authenticateUser(member.getUsername(),member.getPassword());
    }
    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        memberService.logout(authentication);
        return "로그아웃 성공";
    }
}