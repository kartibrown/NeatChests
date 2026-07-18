package com.kartibrown.neatchests;

import com.kartibrown.neatchests.category.Misc;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public abstract class Category {
    public static final int MAX_WEIGHT = 2000;

    protected final Map<Material, Integer>[] items;

    private final int[] nextAvaiableWeights;

    @SuppressWarnings("unchecked")
    public Category(final int numberOfSubCategories) {

        items = (EnumMap<Material, Integer>[]) new EnumMap[numberOfSubCategories];
        nextAvaiableWeights = new int[numberOfSubCategories];

        for (int i = 0; i < numberOfSubCategories; i++) {
            items[i] = new EnumMap<>(Material.class);

            nextAvaiableWeights[i] = MAX_WEIGHT / 2;
        }
    }

    /**
     * Tries to add or check that a material was added to a category
     *
     * @param material The material checked
     * @return Returns true if a material was successfully added to its category
     */
    public abstract boolean tryAdd(final Material material);

    public void add(final Material material, final int weight) {
    }

    /**
     * Adds a material if it exists in this version of Minecraft with an automated weight
     *
     * @param subCategoryIndex which sub category to add it to
     * @param bukkitName       Material Name
     */
    protected final void addMaterialIfExists(final int subCategoryIndex, final String bukkitName) {
        try {
            final Material mat = Material.valueOf(bukkitName);
            addWithAutoWeight(subCategoryIndex, mat);
        } catch (final IllegalArgumentException e) {
            // The block doesn't exist, but we catch
            // the exception quietly
        }
    }

    /**
     * Add a material if it exists in this version of Minecraft with a fixed weight
     *
     * @param subCategoryIndex which sub category to add it to
     * @param bukkitName       Material Name
     * @param weight           The weight to be added
     */
    protected final void addMaterialIfExists(final int subCategoryIndex,
                                             final String bukkitName,
                                             final int weight) {
        try {
            final Material mat = Material.valueOf(bukkitName);
            items[subCategoryIndex].put(mat, weight);
        } catch (final IllegalArgumentException e) {
            // The block doesn't exist, but we catch
            // the exception quietly
        }
    }

    /**
     * Adds to the sub category map with a chosen weight
     */
    protected void addToCategory(final int subCategoryIndex, final Material material, final int weight) {
        items[subCategoryIndex].put(material, Math.clamp(weight, 0, MAX_WEIGHT));
    }

    protected final void addWithAutoWeight(final int subCategoryIndex, final Material material) {
        int currentWeight = nextAvaiableWeights[subCategoryIndex];
        items[subCategoryIndex].put(material, currentWeight);

        // protects it from going under misc's weight
        nextAvaiableWeights[subCategoryIndex] = Math.max(currentWeight - 1, Misc.MISC_MAX_WEIGHT);
    }

    /*
     * GETTERS & SETTERS
     */

    public String[] getTypes() {
        return null;
    }

    public final @Nullable Integer getWeightFor(final @NotNull Material material) {
        for (final Map<Material, Integer> subCategoryMap : items) {
            if (subCategoryMap.containsKey(material)) {
                return subCategoryMap.get(material);
            }
        }
        return null;
    }

    protected final void setBaseWeight(final int subCategoryIndex, final int weight) {
        nextAvaiableWeights[subCategoryIndex] = Math.clamp(weight, 0, MAX_WEIGHT);
    }
}
