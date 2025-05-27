package com.apple.shop;

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