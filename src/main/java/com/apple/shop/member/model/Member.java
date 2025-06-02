package com.apple.shop.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String displayName;
    // 권한 필드 추가
    private String role; // 예: "ROLE_USER"

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", role=" + role + // ⚠ 여기서 items → item → member → items 무한루프 가능
                '}';
    }

}
