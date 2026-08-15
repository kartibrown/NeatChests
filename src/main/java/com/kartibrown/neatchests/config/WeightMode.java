package com.kartibrown.neatchests.config;

public enum WeightMode {
    SIMPLE,
    ADVANCED;

    public static WeightMode fromString(final String mode) {
        if (mode == null) {
            return SIMPLE;
        }

        if (mode.equalsIgnoreCase("simple")) {
            return SIMPLE;
        } else {
            return ADVANCED;
        }
    }
    }
