package com.learningjava.wotapi.shared.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Region {
    EU("EU", "Europe"),
    NA("NA", "North America"),
    ASIA("ASIA", "Asia");

    private final String code;
    private final String displayName;

    Region(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static Region fromCode(String code) {
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
