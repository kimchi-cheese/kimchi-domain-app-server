package com.kimcheese.kimchidomainappserver.domain.photobooth.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import java.util.List;

@Builder
@Getter
@Setter
public class GetPhotoBooth {
    private String username;

    private String lastDocumentId;

    @JsonProperty("h_rate")
    private int hRate;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String location;

    private List<Map<String,String>> sns;

    private List<Map<String, Object>> imageUrls;

}
