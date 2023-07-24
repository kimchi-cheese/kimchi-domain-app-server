package com.kimcheese.kimchidomainappserver.domain.photobooth.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

import com.kimcheese.kimchidomainappserver.domain.region.entity.vo.RegionName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

@Getter
@Setter
public class PhotoBooth {
    private String _id;
    private String userId;

    private Map<String, Float> gps;

    private int hRate;

    @Enumerated(EnumType.STRING)
    private RegionName region;

    private String location;

    private List<Map<String,String>> sns;

    private List<Map<String,Object>> photoIds; //인생 네컷 사진 묶음
}
