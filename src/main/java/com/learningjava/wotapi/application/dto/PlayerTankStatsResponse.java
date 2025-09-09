package com.learningjava.wotapi.application.dto;

import com.learningjava.wotapi.infrastructure.client.tomato.dto.value.StatTotals;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PlayerTankStatsResponse {
    private List<PlayerTankStatResponse> data;

    private StatTotals totals;
    private ArrayList<PlayerTankStatResponse> notInEncyclopedia = new ArrayList<>();
}