package com.learningjava.wotapi.infrastructure.model.value;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class StatTotals {
    private int battlesAll;

    private Map<String, Integer> battlesPerType = new HashMap<>();
    private Map<Integer, Integer> battlesPerTier = new HashMap<>();

    private Map<String, Integer> battlesPerTypePercentage = new HashMap<>();
    private Map<Integer, Integer> battlesPerTierPercentage = new HashMap<>();

    public void addBattles(String type, int tier, int battles) {
        battlesAll += battles;

        battlesPerType.merge(type, battles, Integer::sum);
        battlesPerTier.merge(tier, battles, Integer::sum);
    }

    public void calculatePercentages() {
        if (battlesAll == 0) return;

        for (var entry : battlesPerType.entrySet()) {
            int percentage = (int) Math.round((entry.getValue() * 100.0) / battlesAll);
            battlesPerTypePercentage.put(entry.getKey(), percentage);
        }

        for (var entry : battlesPerTier.entrySet()) {
            int percentage = (int) Math.round((entry.getValue() * 100.0) / battlesAll);
            battlesPerTierPercentage.put(entry.getKey(), percentage);
        }
    }
}
