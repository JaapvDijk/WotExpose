package com.learningjava.wotapi.infrastructure.client.tomato.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.Date;
import java.util.List;

@Generated("")
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
