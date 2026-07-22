package com.kartibrown.neatchests;

import com.kartibrown.NeatChestsPlugin;
import com.kartibrown.neatchests.category.Misc;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public abstract class Category {
    protected static final int MIN_WEIGHT = Material.values().length + 1;
    protected static final int MAX_WEIGHT = MIN_WEIGHT + 2000;

    protected final Map<Material, Integer>[] subCategories;

    private final int[] nextAvailableWeights;

    @SuppressWarnings("unchecked")
    public Category(final int numberOfSubCategories) {

        subCategories = (EnumMap<Material, Integer>[]) new EnumMap[numberOfSubCategories];
        nextAvailableWeights = new int[numberOfSubCategories];

        for (int i = 0; i < numberOfSubCategories; i++) {
            subCategories[i] = new EnumMap<>(Material.class);

            nextAvailableWeights[i] = (MAX_WEIGHT + MIN_WEIGHT) / 2;
        }
    }

    /**
     * Tries to add or check that a material was added to a category
     *
     * @param material The material checked
     * @return Returns true if a material was successfully added to its category
     */
    public abstract boolean tryAdd(final Material material, final int weight);

    /**
     * Most likly only for the FallBack class!
     *
     * @param material The material to add
     * @param weight The weight to add
     */
    public void add(final Material material, final int weight) {
        subCategories[0].put(material, weight);
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
            addToCategory(subCategoryIndex, mat, weight);
        } catch (final IllegalArgumentException e) {
            // The block doesn't exist, but we catch
            // the exception quietly
        }
    }

    /**
     * Adds to the sub category map with a chosen weight
     */
    protected void addToCategory(final int subCategoryIndex, final Material material, final int weight) {
        subCategories[subCategoryIndex].put(material, Math.clamp(weight, MIN_WEIGHT, MAX_WEIGHT));
    }

    /**
     * Adds a material to the desired sub category and automates the weight.<br>
     * Can be used without setBaseWeight() but the weight will be set to MAX_WEIGHT / 2
     *
     * @param subCategoryIndex The desired sub category
     * @param material The item to add
     */
    protected final void addWithAutoWeight(final int subCategoryIndex, final Material material) {
        int currentWeight = nextAvailableWeights[subCategoryIndex];
        subCategories[subCategoryIndex].put(material, currentWeight);

        // protects it from going under misc's (FALLBACK CLASS) weight
        nextAvailableWeights[subCategoryIndex] = Math.max(currentWeight - 1, MIN_WEIGHT);
    }

    /*
     * GETTERS & SETTERS
     */

    protected String[] getTypes() {
        return null;
    }

    /**
     * Gets the weight of a material
     *
     * @param material The material to get the weight from
     * @return Returns the weight of the passed material
     */
    public final @Nullable Integer getWeightFor(final @NotNull Material material) {
        for (final Map<Material, Integer> subCategoryMap : subCategories) {
            if (subCategoryMap.containsKey(material)) {
                return subCategoryMap.get(material);
            }
        }
        return null;
    }

    @Contract(pure = true)
    public final boolean contains(final Material material) {
        for(final Map<Material, Integer> subCategoryMap : subCategories) {
            if (subCategoryMap.containsKey(material)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the base weight for the sub category, default is (MAX_WEIGHT + MIN_WEIGHT) / 2
     *
     * @param subCategoryIndex The sub category to set the weight to
     * @param weight The weight
     */
    protected final void setBaseWeight(final int subCategoryIndex, final int weight) {
        nextAvailableWeights[subCategoryIndex] = Math.clamp(weight, 0, MAX_WEIGHT);
    }
}
