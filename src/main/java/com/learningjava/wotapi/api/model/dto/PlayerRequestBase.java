package com.learningjava.wotapi.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public abstract class PlayerRequestBase {
    @NotBlank(message = "Region is required")
    @Pattern(regexp = "EU|NA|ASIA", message = "Region must be either 'EU', 'NA' or 'ASIA'")
    private String region;

    public PlayerRequestBase(String region) {
        this.region = region;

        //Set the region in the context here
//        if (region != null) {
//            ContextHolder.setRegion(region);
//        }
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
