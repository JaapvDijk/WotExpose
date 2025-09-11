package com.learningjava.wotapi.application.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "api.wargaming")
public class WargamingProperties {

    private String appId;
    private Map<String, String> baseUrls;
}