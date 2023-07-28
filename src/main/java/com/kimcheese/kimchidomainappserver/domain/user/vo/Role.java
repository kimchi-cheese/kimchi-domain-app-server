package com.kimcheese.kimchidomainappserver.domain.user.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_GUEST,
    ROLE_USER,
    ROLE_ADMIN;

}
