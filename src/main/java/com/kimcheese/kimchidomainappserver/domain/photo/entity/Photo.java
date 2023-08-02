package com.kimcheese.kimchidomainappserver.domain.photo.entity;

import com.google.cloud.firestore.DocumentReference;
import com.kimcheese.kimchidomainappserver.core.mixin.TimestampMixin;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Country;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Getter
@Setter
public class Photo extends TimestampMixin {
    public static final String TABLENAME = "Photos";

    private String _id;
    private String username;
    private DocumentReference userId;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Country country;
    
    @Enumerated(EnumType.STRING)
    private Region region;

    private String location;

    private Map<String,Float> gps;
}
