package com.kartibrown.neatchests.config;

import java.util.Locale;

/**
 * Defines how item weights are loaded from {@code weights.yml}.
 */
public enum WeightMode {
    /**
     * Only category weights are configurable.
     */
    SIMPLE,

    /**
     * Both category and individual item weights are configurable.
     */
    ADVANCED;

    /**
     * Converts a configuration string to a {@link WeightMode}.
     *
     * @param mode the configured weight mode
     * @return the matching weight mode, or {@link #SIMPLE} if the value is invalid
     */
    public static WeightMode fromString(final String mode) {
        if ("advanced".equalsIgnoreCase(mode)) {
            return ADVANCED;
        }

        return SIMPLE;
    }
}
