package com.learningjava.wotapi.infrastructure.model.dto.worldoftanks;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

//    images
//
//    Image links
//    images.big_icon	string
//
//    URL to 160 x 100 px image of vehicle
//    images.contour_icon	string
//
//    URL to outline image of vehicle
//    images.small_icon	string
//
//    URL to 124 x 31 px image of vehicle
}
