package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayersResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.learningjava.wotapi.api.config.RestClientConfig.RestClientProxy;

@Component
public class WargamingClient {

    private final RestClientProxy restClient;

    public WargamingClient(@Qualifier("wargamingRestClient") RestClientProxy restClient) {
        this.restClient = restClient;
    }

    //Accounts
    public WoTPlayersResponse getPlayers(String name) {
        return restClient.getRequest()
                .uri(builder ->
                        builder.path("/account/list/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("search", name)
                                .build())
                .retrieve()
                .body(WoTPlayersResponse.class);
    }

    public String getPlayerInfo(int accountId) {
        return restClient.getRequest()
                .uri(builder ->
                        builder.path("/account/info/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("account_id", accountId)
                                .build())
                .retrieve()
                .body(String.class);
    }

    //Player ratings
}
