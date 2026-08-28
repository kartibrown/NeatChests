package com.kartibrown.neatchests.config;

import org.jetbrains.annotations.Contract;

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
     * @return the matching weight mode
     * @throws NullPointerException     if {@code mode} is null or empty
     * @throws IllegalArgumentException if {@code mode} is invalid
     */
    @Contract(pure = true)
    public static WeightMode fromString(final String mode) {
        if (mode == null || mode.isEmpty()) {
            throw new NullPointerException("mode cannot be null or empty");
        }

        return switch (mode.toLowerCase(Locale.ROOT)) {
            case "simple" -> SIMPLE;
            case "advanced" -> ADVANCED;
            default -> throw new IllegalArgumentException("Unknown weight mode: " + mode);
        };
    }
}
