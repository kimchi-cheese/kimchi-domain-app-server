package com.kimcheese.kimchidomainappserver.domain.user.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SocialType{
    GOOGLE("google"),
    APPLE("apple"),
    KAKAO("kakao"),
    NAVAR("naver");
    
    private final String value;
}
