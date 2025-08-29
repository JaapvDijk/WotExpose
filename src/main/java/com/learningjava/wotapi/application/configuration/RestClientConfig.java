package com.learningjava.wotapi.application.configuration;

import com.learningjava.wotapi.infrastructure.HttpContext;
import com.learningjava.wotapi.shared.constant.RegionType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    @Qualifier("tomatoRestClient")
    public RestClient tomatoRestClient() {

        return RestClient.builder()
                .baseUrl(tomatoProperties.getBaseUrl())
                .build();
    }

    @Bean
    @Qualifier("wargamingRestClient")
    public RestClientProxy wargamingRestClient(@Value("${api.wargaming.app-id}") String appId) {
        return new RestClientProxy(appId);
    }

    public class RestClientProxy {

        private final String appId;
        private final Map<RegionType, RestClient> clientCache = new ConcurrentHashMap<>();

        public RestClientProxy(String appId) {
            this.appId = appId;
        }

        public RestClient get() {
            RegionType region = HttpContext.getRegion();
            if (region == null) {
                throw new IllegalStateException("No region set in HttpContext.");
            }

            return clientCache.computeIfAbsent(region, this::buildClient);
        }

        private RestClient buildClient(RegionType region) {
            String baseUrl = switch (region) {
                case RegionType.EU -> wargamingProperties.getBaseUrlEu();
                case RegionType.NA -> wargamingProperties.getBaseUrlNa();
                case RegionType.ASIA -> wargamingProperties.getBaseUrlAsia();
                default -> throw new IllegalArgumentException("Unsupported region: " + region);
            };

            return RestClient.builder()
                    .baseUrl(baseUrl)
                    //.defaultRequest(req -> req.url(url -> url.queryParam("application_id", appId))) would be nice
                    //.requestInitializer(myCustomInitializer)
                    .defaultUriVariables(Map.of("application_id", appId)) //for as long the above has no solution
                    .build();
        }

        public RestClient.RequestHeadersUriSpec<?> getRequest() {
            return get().get();
        }

        public RestClient.RequestBodyUriSpec postRequest() {
            return get().post();
        }
    }
}