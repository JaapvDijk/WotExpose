package com.learningjava.wotexpose.infrastructure.client.wargaming.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.annotation.Generated;

@Generated("")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WoTVehicleResponse {
    private String name;
    @JsonProperty("short_name")
    private String shortName;
    private String nation;
    @JsonProperty("is_premium")
    private boolean isPremium;
    private String tag;
    private String type;
    private int tier;
    @JsonProperty("tank_id")
    private int tankId;
    private Images images;

    @Data
    @NoArgsConstructor
    public static class Images{
        @JsonProperty("small_icon")
        private String smallIcon;
        @JsonProperty("contour_icon")
        private String contourIcon;
        @JsonProperty("big_icon")
        private String bigIcon;
    }
}
