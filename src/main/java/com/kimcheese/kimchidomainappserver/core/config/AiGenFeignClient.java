package com.kimcheese.kimchidomainappserver.core.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name="kimchi-ai-app-service")
public interface AiGenFeignClient {
    @GetMapping("/ai/intro")
    Map<String,String> getAiIntro();

    @PostMapping("/ai/gen/images")
    Map<String,Object> postAiGenImages(@RequestBody AiGenInfo aiGenInfo);

}
