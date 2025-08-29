package com.learningjava.wotapi.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER("ROLE_USER", "User"),
    ADMIN("ROLE_ADMIN", "Admin");

    private final String code;
    private final String displayName;

    public static RoleType fromCode(String code) {
        if (code == null) return null;

        return Arrays.stream(values())
                .filter(role -> role.code.equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown role code: " + code));
    }

    @Override
    public String toString() {
        return code;
    }
}
