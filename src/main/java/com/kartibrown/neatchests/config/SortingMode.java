package com.kartibrown.neatchests.config;

import org.jetbrains.annotations.Contract;

public enum SortingMode {
    CATEGORY,
    FAMILY,
    BLOCK_TYPE;

    /**
     * Converts a configuration string to a {@link SortingMode}.
     *
     * @param mode the configured sorting mode
     * @return the matching sorting mode
     * @throws NullPointerException     if {@code mode} is null or empty
     * @throws IllegalArgumentException if {@code mode} is invalid
     */
    @Contract(pure = true)
    public static SortingMode fromString(final String mode) {
        if (mode == null || mode.isEmpty()) {
            throw new NullPointerException("mode cannot be null or empty");
        }

        return switch (mode.toLowerCase()) {
            case "category" -> CATEGORY;
            case "family" -> FAMILY;
            case "block-type" -> BLOCK_TYPE;
            default -> throw new IllegalArgumentException("Unknown sorting mode: " + mode);
        };
    }
}
