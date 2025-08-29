package com.learningjava.wotapi.infrastructure;

import com.learningjava.wotapi.shared.constant.Region;

public class HttpContext {

    private static final ThreadLocal<Region> regionHolder = new ThreadLocal<>();

    public static void setRegion(Region region) {
        regionHolder.set(region);
    }

    public static Region getRegion() {
        return regionHolder.get();
    }

    public static void clear() {
        regionHolder.remove();
    }
}
