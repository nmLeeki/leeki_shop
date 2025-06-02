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
    @Override
    public Member authenticateUser(String username, String password) {
        // 사용자 정보 조회
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 비밀번호 검증
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }



        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        member.getUsername(),
                        null,
                        authorities
                );


        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 6. 세션 강제 생성 (쿠키 발급 유도)
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            request.getSession(true); // 👉 이 호출이 JSESSIONID 생성 유도
        }

        return member;
    }

    @Override
    public void logout(Authentication authentication) {
        SecurityContextHolder.clearContext();
        // 세션 무효화
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        HttpSession session = request.getSession(false); // 기존 세션만 가져옴
        if (session != null) {
            session.invalidate();
        }

    }

}