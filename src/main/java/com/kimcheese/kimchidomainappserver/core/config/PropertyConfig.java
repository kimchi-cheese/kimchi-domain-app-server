package com.kimcheese.kimchidomainappserver.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:properties/env.properties")})
public class PropertyConfig {
}

