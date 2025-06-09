// CustomLogoutSuccessHandler.java
package com.apple.shop.core.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger("LogoutHandler");

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            logger.info("세션이 이미 무효화되었습니다.");
        } else {
            logger.info("세션이 아직 살아있음. 세션 ID: {}", session.getId());
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("SecurityContext가 정상적으로 클리어되었습니다.");
        } else {
            logger.info("SecurityContext에 인증 정보가 남아있음: {}", SecurityContextHolder.getContext().getAuthentication());
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("로그아웃 성공");
    }
}