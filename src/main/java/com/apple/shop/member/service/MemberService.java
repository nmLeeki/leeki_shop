package com.apple.shop.member.service;

import com.apple.shop.member.model.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService {
    // 회원 가입
    Member join(Member member);

    // 회원 정보 조회
    Member findMember(Long memberId);

    // 회원 정보 수정
    Member updateMember(Long memberId, Member member);

    // 회원 탈퇴
    void deleteMember(Long memberId);

}