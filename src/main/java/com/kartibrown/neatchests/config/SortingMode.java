package com.kartibrown.neatchests.config;

import org.jetbrains.annotations.Contract;

public enum SortingMode {
    CATEGORY,
    FAMILY,
    BLOCK_TYPE;

    @Contract(pure = true)
    public static SortingMode fromString(final String mode) {
        if ("block-type".equalsIgnoreCase(mode)) {
            return BLOCK_TYPE;
        } else if ("family".equalsIgnoreCase(mode)) {
            return FAMILY;
        } else {
            return CATEGORY;
        }
    }
}
