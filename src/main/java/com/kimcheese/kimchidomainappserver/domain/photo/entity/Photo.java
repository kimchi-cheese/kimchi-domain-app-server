package com.kimcheese.kimchidomainappserver.domain.photo.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

import com.kimcheese.kimchidomainappserver.domain.region.entity.vo.RegionName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Getter
@Setter
public class Photo {
    private String _id;
    private String username;
    private String userId;
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private RegionName region;

    private String location;
    private Map<String,Float> gps;
}
