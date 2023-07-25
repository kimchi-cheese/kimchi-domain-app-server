package com.kimcheese.kimchidomainappserver.domain.user.entity;

import com.kimcheese.kimchidomainappserver.core.mixin.TimestampMixin;
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
public class User extends TimestampMixin {
    private String _id;
    private String email;
    private String password;
    private int age = -1;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String profilePath;
    
    private List<Map<String,Object>> photoBoothIds; // 내 보관함 사진 모음
    // _id


    private String refreshToken;
}
