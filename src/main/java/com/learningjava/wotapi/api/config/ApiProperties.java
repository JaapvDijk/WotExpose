package com.learningjava.wotapi.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private Tomato tomato = new Tomato();
    private Wargaming wargaming = new Wargaming();

    public Tomato getTomato() {
        return tomato;
    }

    public void setTomato(Tomato tomato) {
        this.tomato = tomato;
    }

    public Wargaming getWargaming() {
        return wargaming;
    }

    public void setWargaming(Wargaming wargaming) {
        this.wargaming = wargaming;
    }

    public static class Tomato {
        private String baseUrl;
        private String scheduleExpression;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getScheduleExpression() {
            return this.scheduleExpression;
        }

        public void setScheduleExpression(String scheduleExpression) {
            this.scheduleExpression = scheduleExpression;
        }
    }

    public static class Wargaming {
        private String appId;
        private String baseUrlEu;
        private String baseUrlNa;
        private String baseUrlAsia;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

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
    }
}