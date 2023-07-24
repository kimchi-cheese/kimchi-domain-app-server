package com.kimcheese.kimchidomainappserver.domain.user.entity;

import com.kimcheese.kimchidomainappserver.domain.user.vo.Role;
import com.kimcheese.kimchidomainappserver.domain.user.vo.SocialType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class User {
    private String _id;
    private String email;
    private String password;
    private int age = 0;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String refreshToken;

    private String profilePath;
    
    private List<Map<String,Object>> photoIds; // 내 보관함 사진 모음

}
