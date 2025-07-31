package com.learningjava.wotapi.api.model.tomato.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TomatoTankPerformanceResponse {
    private List<TankPerformanceResponse> data;
    private Meta meta;

    @Data
    @NoArgsConstructor
    public static class Meta {
        private String status;
        private Date updated;
    }
}
