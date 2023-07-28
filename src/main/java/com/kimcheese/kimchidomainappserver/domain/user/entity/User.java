package com.kimcheese.kimchidomainappserver.domain.user.entity;

import com.kimcheese.kimchidomainappserver.core.mixin.TimestampMixin;
import com.kimcheese.kimchidomainappserver.domain.user.vo.Role;
import com.kimcheese.kimchidomainappserver.domain.user.vo.SocialType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class User extends TimestampMixin {
    public static final String TABLENAME = "Users";

    private String _id;
    private String nickname;
    private String email;
    private String password;
    private int age = -1;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    private String profilePath;
    
    private List<Map<String,Object>> photoBoothIds; // 내 보관함 사진 모음
    // _id

    private String refreshToken;

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.ROLE_USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    //== 유저 필드 업데이트 ==//
    public void updateNickname(String updateNickname) {
        this.nickname = updateNickname;
    }

    public void updateAge(int updateAge) {
        this.age = updateAge;
    }

    public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(updatePassword);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
