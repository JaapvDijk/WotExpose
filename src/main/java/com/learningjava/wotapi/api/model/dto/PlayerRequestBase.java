package com.learningjava.wotapi.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PlayerRequestBase {
    @NotBlank(message = "Region is required")
    @Pattern(regexp = "EU|NA|ASIA", message = "Region must be either 'EU', 'NA' or 'ASIA'")
    private String region;
}
