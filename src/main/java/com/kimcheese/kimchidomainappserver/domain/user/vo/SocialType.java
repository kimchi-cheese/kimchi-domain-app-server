package com.kimcheese.kimchidomainappserver.domain.user.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SocialType{
    GOOGLE,
    APPLE,
    KAKAO,
    NAVER;
}
