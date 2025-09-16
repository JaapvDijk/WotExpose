package com.learningjava.wotexpose.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RegionType {
    EU("EU", "Europe"),
    NA("NA", "North America"),
    ASIA("ASIA", "Asia");

    private final String code;
    private final String displayName;

    public static RegionType fromCode(String code) {
        if (code == null) return null;

        return Arrays.stream(values())
                .filter(region -> region.code.equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown region code: " + code));
    }

    @Override
    public String toString() {
        return code;
    }
}
