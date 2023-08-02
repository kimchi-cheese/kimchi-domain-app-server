package com.kimcheese.kimchidomainappserver.core.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AiGenInfo {
    private String id;
    private String image_url;
    private String prompt;
}
