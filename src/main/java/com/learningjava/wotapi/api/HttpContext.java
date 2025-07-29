package com.learningjava.wotapi.api;

public class HttpContext {

    private static final ThreadLocal<String> regionHolder = new ThreadLocal<>();

    public static void setRegion(String region) {
        regionHolder.set(region.toUpperCase());
    }

    public static String getRegion() {
        return regionHolder.get();
    }

    public static void clear() {
        regionHolder.remove();
    }
}
