package com.apple.shop.core.config.security;

import com.apple.shop.member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Spring Security의 핵심 보안 필터 체인 설정
     *
     * 파라미터 설명:
     * - HttpSecurity http
     *   └ Spring Security의 보안 정책(인증, 인가, CSRF, CORS, 로그인/로그아웃 등)을 설정하는 핵심 객체입니다.
     *
     * - CustomAuthenticationSuccessHandler successHandler
     *   └ 로그인 성공 시 실행되는 커스텀 핸들러입니다.
     *     (예: 로그인 성공 후 추가 로직, 응답 커스터마이징 등)
     *
     * - CustomAuthenticationFailureHandler failureHandler
     *   └ 로그인 실패 시 실행되는 커스텀 핸들러입니다.
     *     (예: 실패 메시지 반환, 실패 횟수 기록 등)
     *
     * - CustomLogoutSuccessHandler logoutSuccessHandler
     *   └ 로그아웃 성공 시 실행되는 커스텀 핸들러입니다.
     *     (예: 로그아웃 후 세션/컨텍스트 상태 확인, 응답 메시지 등)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomAuthenticationSuccessHandler successHandler,
            CustomAuthenticationFailureHandler failureHandler,
            CustomLogoutSuccessHandler logoutSuccessHandler
    ) throws Exception {
        // CORS 설정 (기본값 사용)
        http.cors(Customizer.withDefaults());

        // CSRF 보호 비활성화 (API 서버 등에서 주로 사용)
        http.csrf(AbstractHttpConfigurer::disable);

        // 인가(Authorization) 설정
        http.authorizeHttpRequests(authz -> authz
                // 모든 경로에 대해 인증 없이 접근 허용
                .requestMatchers("/**").permitAll()
                // 위에서 명시하지 않은 나머지 요청은 인증 필요
                .anyRequest().authenticated()
        );

        // 폼 로그인 설정
        http.formLogin(form -> form
                // React가 처리할 로그인 “페이지” 경로
                .loginPage("/login")
                // 실제 인증 처리(POST)는 /api/login 으로
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                // 이 두 경로에 대해서는 인증 없이 접근 허용
                .permitAll()
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                // 로그아웃 처리 URL 지정
                .logoutUrl("/logout")
                // 로그아웃 성공 시 커스텀 핸들러 실행
                .logoutSuccessHandler(logoutSuccessHandler)
        );

        // 최종적으로 SecurityFilterChain 반환
        return http.build();
    }

    /**
     * 인증 매니저 빈 등록 (커스텀 인증 처리에 필요)
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * UserDetailsService 빈 등록 (DB에서 사용자 정보 조회)
     */
    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return new CustomUserDetailsService(memberRepository);
    }

    /**
     * 비밀번호 암호화에 사용할 PasswordEncoder 빈 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}