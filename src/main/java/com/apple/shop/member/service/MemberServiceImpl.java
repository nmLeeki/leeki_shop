package com.apple.shop.member.service;
import java.util.List;
import java.util.ArrayList;
import com.apple.shop.member.model.Member;
import com.apple.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


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
            // 로그인 유저 정보
            System.out.println("로그인 성공");
        }

        // 권한 정보를 리스트로 변환
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        System.out.println("권한 정보: " + authorities);
        // username과 displayName만 가진 Member 객체 생성
        // 로그인 유저 정보
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member.getUsername(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Member result = new Member();
        result.setUsername(member.getUsername());
        result.setDisplayName(member.getDisplayName());
        result.setRole(member.getRole());
        return result;
    }

    @Override
    public Member getCurrentMember(Authentication auth) {
        System.out.println(auth);
        if (auth == null || !auth.isAuthenticated()) {
            System.out.println("인증 정보 없음");
            return null;
        }
        String username = auth.getName();
        System.out.println("username: " + username);
        return memberRepository.findByUsername(username)
                .orElse(null);
    }
}