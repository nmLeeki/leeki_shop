package com.apple.shop.member.service;

import com.apple.shop.member.model.Member;
import com.apple.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Member join(Member member)  {
        if (member.getUsername().length() < 4 ||
               member.getPassword().length() < 4) {
            throw new IllegalArgumentException("Username and password must be at least 8 characters long");
        }

        if (member.getDisplayName() == null || member.getDisplayName().isEmpty()) {
            throw new IllegalArgumentException("Display name cannot be empty");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);// 실제로는 memberRepository.save(member)와 같은 로직이 필요합니다.
    }

    @Override
    public Member findMember(Long memberId) {
        return null;
    }

    @Override
    public Member updateMember(Long memberId, Member member) {
        return null;
    }

    @Override
    public void deleteMember(Long memberId) {

    }
    @Override
    public Member login(String username, String password)  {
        Member member = (Member) memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }else{
            System.out.println("로그인 성공");
        }

        // username과 displayName만 가진 Member 객체 생성
        Member result = new Member();
        result.setUsername(member.getUsername());
        result.setDisplayName(member.getDisplayName());
        return result;
    }
}