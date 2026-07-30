package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public final class Forestry extends Category {
    private static final int LOGS = 0;
    private static final int PLANKS = 1;
    private static final int SLABS = 2;
    private static final int DOORS = 3;
    private static final int MISC = 4;
    private static final int SAPLING = 5;

    private static final int LOGS_OFFSET = 0;
    private static final int PLANKS_OFFSET = 50;
    private static final int SLABS_OFFSET = 100;
    private static final int DOORS_OFFSET = 150;
    private static final int MISC_OFFSET = 200;
    private static final int SAPLING_OFFSET = 250;

    private final String[] woodTypes;

    public Forestry() {
        super(6);

        woodTypes = getTypes();
    }

    @Override
    public boolean tryAdd(final @NonNull Material material) {
        final String name = material.name();

        // Wood type gatekeeper
        boolean isWoodFamily = false;
        for (final String woodType : woodTypes) {
            if (name.contains(woodType)) {
                isWoodFamily = true;
                break;
            }
        }

        /*
         * If this list gets big, consider using a 
         */
        // Universal item fallback
        if (name.equals("STICK") || name.equals("BOWL") || name.equals("LADDER")) {
            addToCategory(MISC, material, getWeightOffset(MISC_OFFSET));
            return true;
        }

        // if it's a wood type
        if (!isWoodFamily) {
            return false;
        }

        // --- SORTING THE WOOD FAMILY ---

        //Add saplings to the saplings sub-category
        if (name.startsWith("POTTED_") || name.endsWith("_SAPLING")) {
            addToCategory(SAPLING, material, getWeightOffset(SAPLING_OFFSET));
            return true;
        }

        // Manage not a block things
        if (!material.isBlock()) {
            addToCategory(MISC, material, getWeightOffset(MISC_OFFSET));
            return true;
        }

        // Filter out specific tree things that's gonna go to MISC
        // which is otherwise gonna get into LOGS & PLANKS otherwise
        if (name.contains("STAIRS") || name.contains("FENCE") || name.contains("GATE") ||
                name.contains("BUTTON") || name.contains("PLATE") || name.contains("SIGN")) {

            addToCategory(MISC, material, getWeightOffset(MISC_OFFSET));
            return true;
        }

        // Sort the other building blocks
        if (name.contains("SLAB") || name.contains("STEP")) {
            addToCategory(SLABS, material, getWeightOffset(SLABS_OFFSET));
            return true;
        } else if (name.contains("DOOR")) {
            // Both TRAPDOORS and DOORS
            addToCategory(DOORS, material, getWeightOffset(DOORS_OFFSET));
            return true;
        } else if (name.contains("PLANKS") || name.equals("WOOD")) {
            // Gets modern planks and also 1.12 legacy "WOOD"
            addToCategory(PLANKS, material, getWeightOffset(PLANKS_OFFSET));
            return true;
        } else if (name.contains("LOG") || name.contains("WOOD") ||
                name.contains("STEM") || name.contains("HYPHAE") ||
                name.contains("BARK")) {
            // Catches all logs and stuff
            addToCategory(LOGS, material, getWeightOffset(LOGS_OFFSET));
            return true;
        } else {
            // Leaves, saplings etc.
            addToCategory(MISC, material, getWeightOffset(MISC_OFFSET));
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
