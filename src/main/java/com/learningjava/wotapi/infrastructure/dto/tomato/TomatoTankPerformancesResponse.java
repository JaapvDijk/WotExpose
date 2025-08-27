package com.learningjava.wotapi.infrastructure.dto.tomato;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TomatoTankPerformancesResponse {
    private List<TomatoTankPerformanceResponse> data;
    private Meta meta;

    @Data
    @NoArgsConstructor
    public static class Meta {
        private String status;
        private Date updated;
    }
}
