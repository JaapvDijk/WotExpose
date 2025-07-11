package com.learningjava.wotapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    @Qualifier("tomatoRestClient")
    public RestClient tomatoRestClient(@Value("${api.tomato.base-url}") String tomatoApiBaseUrl) {

        return RestClient.builder()
                .baseUrl(tomatoApiBaseUrl)
                .build();
    }

    @Bean
    @Qualifier("wargamingRestClient")
    public RestClient wargamingRestClient(@Value("${api.wargaming.base-url}") String wargamingApiBaseUrl) {
        return RestClient.builder()
                .baseUrl(wargamingApiBaseUrl)
                .build();

        //.requestInterceptor((request, body, execution) -> {
        //request.getHeaders().add("application_id", appId); //TODO: not header but query param
        //return execution.execute(request, body);
    }
}