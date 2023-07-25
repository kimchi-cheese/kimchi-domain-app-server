package com.kimcheese.kimchidomainappserver.domain.location.entity;

import com.kimcheese.kimchidomainappserver.domain.location.vo.Country;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;
import lombok.Getter;

import java.util.Map;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Getter
public class Location {
    private String _id;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String message;

    private Map<String,Float> gps;
    
    private boolean isReleased;
}
