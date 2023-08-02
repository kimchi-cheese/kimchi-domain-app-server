package com.kimcheese.kimchidomainappserver.domain.photobooth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Country;
import com.kimcheese.kimchidomainappserver.domain.location.vo.Region;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public class PostPhotoBooth {
    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String location;

    private String formattedAddress;

    private Map<String, Float> gps;

    private List<Map<String,Object>> photos;
    // id
    // result_url

}
