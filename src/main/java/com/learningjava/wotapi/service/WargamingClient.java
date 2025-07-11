package com.learningjava.wotapi.service;

import com.learningjava.wotapi.api.model.wargaming.Players;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WargamingClient {

    private final RestClient restClient;
    private final String appId;

    public WargamingClient(@Qualifier("wargamingRestClient") RestClient restClient,
                           @Value("${api.wargaming.app-id}") String appId) {
        this.restClient = restClient;
        this.appId = appId;
    }

    //Accounts
    public Players getPlayers(String name) {
        return restClient.get()
                .uri(builder ->
                        builder.path("/account/list/")
                                .queryParam("application_id", appId)
                                .queryParam("search", name)
                                .build())
                .retrieve()
                .body(Players.class);
    }

    public String getPlayerInfo(int accountId) {
        return restClient.get()
                .uri(builder ->
                        builder.path("/account/info/")
                                .queryParam("application_id", appId)
                                .queryParam("account_id", accountId)
                                .build())
                .retrieve()
                .body(String.class);
    }

    //Authentication

    //Player ratings
}
