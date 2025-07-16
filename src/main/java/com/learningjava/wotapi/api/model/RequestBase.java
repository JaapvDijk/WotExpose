package com.learningjava.wotapi.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RequestBase {
    @NotBlank(message = "Region is required")
    @Pattern(regexp = "EU|NA|ASIA", message = "Region must be either 'EU', 'NA' or 'ASIA'")
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
