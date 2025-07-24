package com.learningjava.wotapi.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.wargaming")
public class WargamingProperties {

    private String baseUrlEu;
    private String baseUrlNa;
    private String baseUrlAsia;
    private String appId;

    public String getBaseUrlEu() {
        return baseUrlEu;
    }

    public void setBaseUrlEu(String baseUrlEu) {
        this.baseUrlEu = baseUrlEu;
    }

    public String getBaseUrlNa() {
        return baseUrlNa;
    }

    public void setBaseUrlNa(String baseUrlNa) {
        this.baseUrlNa = baseUrlNa;
    }

    public String getBaseUrlAsia() {
        return baseUrlAsia;
    }

    public void setBaseUrlAsia(String baseUrlAsia) {
        this.baseUrlAsia = baseUrlAsia;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}