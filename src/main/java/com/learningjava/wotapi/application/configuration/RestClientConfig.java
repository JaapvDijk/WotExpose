package com.learningjava.wotapi.application.configuration;

import com.learningjava.wotapi.infrastructure.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RestClientConfig {
    private final WargamingProperties wargamingProperties;
    private final TomatoProperties tomatoProperties;

    public RestClientConfig(WargamingProperties wargamingProperties, TomatoProperties tomatoProperties) {
        this.wargamingProperties = wargamingProperties;
        this.tomatoProperties = tomatoProperties;
    }

    @Bean
    public RestClient tomatoRestClient() {

        return RestClient.builder()
                .baseUrl(tomatoProperties.getBaseUrl())
                .build();
    }

    @Bean
    public WargamingRestClientProxy wargamingRestClient() {
        return new WargamingRestClientProxy();
    }

    public class WargamingRestClientProxy {

        private final String appId;
        private final Map<String, String> baseUrls;
        private final Map<String, RestClient> clientCache = new ConcurrentHashMap<>();

        public WargamingRestClientProxy() {
            this.appId = wargamingProperties.getAppId();
            this.baseUrls = wargamingProperties.getBaseUrls();
        }

        public RestClient.RequestHeadersUriSpec<?> get() {
            String region = HttpContext.getRegion().name().toLowerCase();
            return clientCache.computeIfAbsent(region, r -> {
                String baseUrl = baseUrls.get(r);
                if (baseUrl == null) {
                    throw new IllegalStateException("No base URL configured for region: " + r);
                }
                return RestClient.builder()
                        .baseUrl(baseUrl)
                        .defaultUriVariables(Map.of("application_id", appId))
                        .build();
            }).get();
        }
    }
}