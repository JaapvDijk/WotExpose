package com.learningjava.wotexpose.infrastructure.client.wargaming;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotexpose.shared.exception.PlayerNotFoundException;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTPlayerInfoResponse;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTPlayerResponse;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTPlayerTankStatResponse;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTVehicleResponse;
import org.springframework.stereotype.Component;
import com.learningjava.wotexpose.application.configuration.RestClientConfig.WargamingRestClientProxy;

import java.util.List;

@Component
public class WargamingClient {

    private final WargamingRestClientProxy restClient;
    private final ObjectMapper objectMapper;

    //TODO: add access token for private info .queryParam("access_token", access_token)
    public WargamingClient(WargamingRestClientProxy restClient,
                           ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    public List<WoTPlayerResponse> getPlayers(String name) {
        var root = restClient.get()
                .uri(builder ->
                        builder.path("/account/list/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("search", name)
                                .build())
                .retrieve()
                .body(JsonNode.class);

        var dataNode = getData(root);

        return objectMapper.convertValue(dataNode, new TypeReference<>() {});
    }

    public WoTPlayerInfoResponse getPlayerInfo(int accountId) {
        var root = restClient.get()
                .uri(builder ->
                        builder.path("/account/info/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("account_id", accountId)
                                .build())
                .retrieve()
                .body(JsonNode.class);

        var dataNode = getAccountData(accountId, root);

        return objectMapper.convertValue(dataNode, WoTPlayerInfoResponse.class);
    }

    public List<WoTPlayerTankStatResponse> getPlayerTanks(int accountId) {
        var root = restClient.get()
                .uri(builder ->
                        builder.path("/tanks/stats/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("account_id", accountId)
                                .build())
                .retrieve()
                .body(JsonNode.class);

        var dataNode = getAccountData(accountId, root);

        return objectMapper.convertValue(dataNode, new TypeReference<>() {});
    }

    public List<WoTVehicleResponse> getVehicles() {
        var root = restClient.get()
                .uri(builder ->
                        builder.path("/encyclopedia/vehicles/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("fields", "tank_id,tag,short_name,name,type,tier,nation,is_premium,images") //because otherwise it returns a massive object with unnecessary data
                                .build())
                .retrieve()
                .body(JsonNode.class);

        var dataNode = getData(root);

        return dataNode.properties().stream()
                .map(entry -> objectMapper.convertValue(entry.getValue(), WoTVehicleResponse.class))
                .toList();
    }

    private JsonNode getData(JsonNode root) {
        if (root == null || !root.has("data")) {
            throw new IllegalStateException("Response JSON missing 'data' node");
        }

        return root.get("data");
    }

    private JsonNode getAccountData(int accountId, JsonNode root) {
        var data = getData(root);

        JsonNode dataNode = data.get(String.valueOf(accountId));
        if (dataNode == null || dataNode.isNull()) {
            throw new PlayerNotFoundException("No player found for account ID: " + accountId);
        }

        return dataNode;
    }
}
