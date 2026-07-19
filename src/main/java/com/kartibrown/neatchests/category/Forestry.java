package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public final class Forestry extends Category {
    private static final int LOGS = 0;
    private static final int PLANKS = 1;
    private static final int SLABS = 2;
    private static final int DOORS = 3;
    private static final int MISC = 4;

    private static final int LOGS_WEIGHT = 700;
    private static final int PLANKS_WEIGHT = 650;
    private static final int SLABS_WEIGHT = 600;
    private static final int DOORS_WEIGHT = 550;
    private static final int MISC_WEIGHT = 500;

    private final String[] woodTypes;

    public Forestry() {
        super(5);

        woodTypes = getTypes();
    }

    @Override
    public boolean tryAdd(final @NonNull Material material) {
        final String name = material.name();

        // 1. Wood type gatekeeper
        boolean isWoodFamily = false;
        for (final String woodType : woodTypes) {
            if (name.contains(woodType)) {
                isWoodFamily = true;
                break;
            }
        }

        // Universal item fallback
        if (name.contains("STICK")) {
            addToCategory(MISC, material, MISC_WEIGHT);
            return true;
        }

        if (!isWoodFamily) {
            return false;
        }

        // --- SORTING THE WOOD FAMILY ---

        // Manage not a block things
        if (!material.isBlock()) {
            addToCategory(MISC, material, MISC_WEIGHT);
            return true;
        }

        // Filter out specific tree things that's gonna go to MISC
        // which is otherwise gonna get into LOGS & PLANKS otherwise
        if (name.contains("STAIRS") || name.contains("FENCE") || name.contains("GATE") ||
                name.contains("BUTTON") || name.contains("PLATE") || name.contains("SIGN")) {

            addToCategory(MISC, material, MISC_WEIGHT);
            return true;
        }

        // Sort the other building blocks
        if (name.contains("SLAB") || name.contains("STEP")) {
            addToCategory(SLABS, material, SLABS_WEIGHT);
            return true;
        } else if (name.contains("DOOR")) {
            // Both TRAPDOORS and DOORS
            addToCategory(DOORS, material, DOORS_WEIGHT);
            return true;
        } else if (name.contains("PLANKS") || name.equals("WOOD")) {
            // Gets modern planks and also 1.12 legacy "WOOD"
            addToCategory(PLANKS, material,  PLANKS_WEIGHT);
            return true;
        } else if (name.contains("LOG") || name.contains("WOOD") ||
                name.contains("STEM") || name.contains("HYPHAE") ||
                name.contains("BARK")) {
            // Catches all logs and stuff
            addToCategory(LOGS, material,   LOGS_WEIGHT);
            return true;
        } else {
            // Leaves, saplings etc.
            addToCategory(MISC, material,  MISC_WEIGHT);
            return true;
        }
    }

    @Contract(value = " -> new", pure = true)
    @Override
    protected String @NonNull [] getTypes() {
        return new String[]{
                "ACACIA",
                "BAMBOO",
                "BIRCH",
                "CHERRY",
                "CRIMSON",
                "DARK_OAK",
                "JUNGLE",
                "MANGROVE",
                "OAK",
                "PALE_OAK",
                "SPRUCE",
                "WARPED"
        };
    }
}
