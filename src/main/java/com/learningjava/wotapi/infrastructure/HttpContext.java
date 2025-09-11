package com.learningjava.wotapi.infrastructure;

import com.learningjava.wotapi.shared.constant.RegionType;

public class HttpContext {

    private HttpContext() {}

    private static final ThreadLocal<RegionType> regionHolder = new ThreadLocal<>();

    public static void setRegion(RegionType region) {
        regionHolder.set(region);
    }

    public static RegionType getRegion() {
        return regionHolder.get();
    }

    public static void clear() {
        regionHolder.remove();
    }
}