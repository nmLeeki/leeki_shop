package com.apple.shop.member.service;

import com.apple.shop.member.model.Member;
import com.apple.shop.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;


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

}