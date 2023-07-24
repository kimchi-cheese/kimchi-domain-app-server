package com.kimcheese.kimchidomainappserver.domain.region.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Getter
public class Region {
    private String _id;

    @Enumerated(EnumType.STRING)
    private String name;

    private String message;

    private Map<String,Float> gps;
    
    private boolean isReleased;
}
