package com.kimcheese.kimchidomainappserver.domain.photobooth.dto;

import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import java.util.List;

@Builder
@Getter
@Setter
public class GetPhotoBooth {
    private String username;

    private String lastDocumentId;

    private List<Map<String,Object>> hrate;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String location;

    private List<Map<String,String>> sns;

    private List<Map<String, Object>> imageUrls;

}
