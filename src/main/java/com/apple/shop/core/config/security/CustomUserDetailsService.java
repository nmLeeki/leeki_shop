// src/main/java/com/apple/shop/core/config/security/CustomUserDetailsService.java
package com.apple.shop.core.config.security;

import com.apple.shop.member.model.Member;
import com.apple.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

// ⚠️ 주의: 이 클래스는 SecurityConfig에서 @Bean으로 직접 등록하므로
// @Service 어노테이션을 붙이면 안 됨!
// @Service와 @Bean이 동시에 적용되면 Spring이 같은 타입의 빈을 두 번 등록하게 되어
// 프록시 순환참조 또는 StackOverflowError(무한 재귀)가 발생할 수 있음.
// 반드시 둘 중 하나만 사용해야 함. (여기서는 @Bean만 사용)

//@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        CustomUser customUser = new CustomUser(
                member.getUsername(),
                member.getPassword(),
                List.of() // 권한 필요시 추가
        );
        customUser.id = member.getId();
        customUser.displayName = member.getDisplayName();
        return customUser;
    }
}