package com.kimcheese.kimchidomainappserver.domain.photobooth.entity;

import com.google.cloud.firestore.DocumentReference;
import com.kimcheese.kimchidomainappserver.core.mixin.TimestampMixin;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Country;
import com.kimcheese.kimchidomainappserver.domain.user.vo.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Map;
import java.util.List;

@Getter
@Setter
public class PhotoBooth extends TimestampMixin {

    public static final String TABLENAME = "PhotoBooths";

    private String _id;
    private String email;
    private DocumentReference userId;
    private String username;

    private List<Map<String,Object>> hrate;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String location;

    private String formattedAddress;

    private Map<String, Float> gps;

    private List<Map<String,String>> sns;

    private List<Map<String,Object>> photos;
    //인생 네컷 사진 묶음
    // photoRefId : photo 
    // location : 장소
    // imageUrl;

    public void authorizeUser() {
        this.region = Region.DAEJEON;
    }
}
