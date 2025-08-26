package com.learningjava.wotapi.api.model.dto;

import com.learningjava.wotapi.api.model.shared.StatTotals;
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