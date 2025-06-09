// src/main/java/com/apple/shop/core/config/security/CustomAuthenticationSuccessHandler.java
package com.apple.shop.core.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");

       CustomUser userDetails = (CustomUser) authentication.getPrincipal();

        String json = String.format(
                "{\"username\": \"%s\", \"displayName\": \"%s\", \"authorities\": \"%s\"}",
                userDetails.getUsername(),
                userDetails.displayName,
                userDetails.getAuthorities().toString()
        );
        response.getWriter().write(json);
        // Authentication 객체의 주요 정보 출력
        System.out.println("=== Authentication 정보 디버깅 ===");
        System.out.println("getName(): " + authentication.getName());
        System.out.println("getPrincipal(): " + authentication.getPrincipal());
        System.out.println("getAuthorities(): " + authentication.getAuthorities());
        System.out.println("getCredentials(): " + authentication.getCredentials());
        System.out.println("getDetails(): " + authentication.getDetails());
        System.out.println("isAuthenticated(): " + authentication.isAuthenticated());
        System.out.println("toString(): " + authentication);
    }
}