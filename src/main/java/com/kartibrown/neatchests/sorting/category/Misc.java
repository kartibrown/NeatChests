package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.config.SortingMode;
import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

// FALLBACK CLASS
public final class Misc extends Category {

    private static final int OTHER = 0;

    public Misc() {
        super(1);
    }

    @Override
    public void initialize(final SortingMode sortingMode) {
        // Nothing here because we don't want to set the startWeight variable yet
    }

    @Contract(pure = true)
    @Override
    public boolean containsOrRegister(final Material material) {
        return false;
    }

    /**
     * The fallback category needs adjusted weights to keep related materials
     * together. For example, DIRT should stay close to PODZOL and MYCELIUM
     * even though they do not start with the same letter.
     *
     * @param material The fallback material to add
     * @param weight   The weight to be added with it
     */
    public void addFallback(final Material material, final int weight) {
        final int actualWeight = baseWeight + weight;

        initializeStartWeight(actualWeight);

        if (tryAddGroupedMaterial(material, actualWeight)) {
            return;
        }

        add(material, actualWeight);
    }

    private boolean tryAddGroupedMaterial(final Material material, final int weight) {
        if (subCategories[OTHER].containsKey(material)) {
            return true;
        }

        if (material != Material.DIRT) {
            return false;
        }

        addToCategory(OTHER, Material.DIRT, weight);
        addToCategory(OTHER, Material.GRASS_BLOCK, weight);
        addToCategory(OTHER, Material.COARSE_DIRT, weight);
        addToCategory(OTHER, Material.PODZOL, weight);

        addMaterialIfExists(OTHER, "ROOTED_DIRT", weight);
        addMaterialIfExists(OTHER, "DIRT_PATH", weight);
        addMaterialIfExists(OTHER, "MYCELIUM", weight);

        return true;
    }
}
