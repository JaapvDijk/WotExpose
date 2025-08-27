package com.learningjava.wotapi.application.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "api.wargaming")
public class WargamingProperties {

    private String baseUrlEu;
    private String baseUrlNa;
    private String baseUrlAsia;
    private String appId;
}