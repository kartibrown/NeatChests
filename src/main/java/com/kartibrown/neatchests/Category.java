package com.kartibrown.neatchests;

import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.*;

public abstract class Category {

    protected static final int MAX_WEIGHT = 2000;

    protected final Map<Material, Integer>[] items;

    @SuppressWarnings("unchecked")
    public Category(final int numberOfSubCategories) {

        items = (EnumMap<Material, Integer>[]) new EnumMap[numberOfSubCategories];

        for (int i = 0; i < numberOfSubCategories; i++) {
            items[i] = new EnumMap<>(Material.class);
        }
    }

    /**
     * Tries to add or check that a material was added to a category
     *
     * @param material The material checked
     * @return Returns true if a material was successfully added to its category
     */
    public abstract boolean tryAdd(final Material material);

    /**
     * Adds a material if it exists in this version of Minecraft
     *
     * @param bukkitName Material Name
     */
    protected final void addMaterialIfExists(final Map<Material, Integer> map,
                                             final String bukkitName,
                                             final int weight) {
        try {
            final Material mat = Material.valueOf(bukkitName);
            // Protects from going over MAX_WEIGHT and under 0
            map.put(mat, Math.clamp(weight, 0, MAX_WEIGHT));
        } catch (final IllegalArgumentException e) {
            // The block doesn't exist, but we catch
            // the exception quietly
        }
    }

    /**
     * Adds a material if it exists in this version of Minecraft with a weight of (0)
     *
     * @param bukkitName Material Name
     */
    protected final void addMaterialIfExists(final Map<Material, Integer> map,
                                             final String bukkitName) {
        addMaterialIfExists(map, bukkitName, 0);
    }

    /**
     * Creates a weighted map from the given materials with a decreasing weight starting
     * from half of MAX_WEIGHT.
     *
     * @param materials the materials to assign weights to, in order of priority
     * @return a map containing the materials and their corresponding weights
     */
    @NotNull
    protected final Map<Material, Integer> createMap(final Material @NonNull ... materials) {
        return createMap(true, materials);
    }

    /**
     * Creates a map from the given materials. The weights can either decrease
     * sequentially to enforce strict sorting order, or be set to zero for standard sorting.
     *
     * @param weighted  if true, weights decrease sequentially starting from half of MAX_WEIGHT;
     *                  if false, all materials receive a weight of 0.
     * @param materials the materials to add to the map, in order of appearance.
     * @return a high-performance EnumMap containing the materials and their assigned weights.
     */
    @NotNull
    protected final Map<Material, Integer> createMap(final boolean weighted, final Material @NonNull ... materials) {
        final Map<Material, Integer> map = new EnumMap<>(Material.class);

        // If unweighted, we start at 0. If weighted, we start at MAX_WEIGHT / 2 to leave some headroom.
        int currentWeight = (weighted) ? MAX_WEIGHT / 2 : 0;

        for (final Material material : materials) {
            map.put(material, currentWeight);
            // Only decrease if we are actually using weights, and protect from going below 0
            if (weighted) {
                currentWeight = Math.max(currentWeight - 1, 0);
            }
        }

        return map;
    }

    /**
     * Adds to the sub category map with a chosen weight
     */
    protected void addToCategory(final int subCategoryIndex, final Material material, final int weight) {
        items[subCategoryIndex].put(material, weight);
    }

    /**
     * Adds to the sub category map with a weight of (0)
     */
    protected void addToCategory(final int subCategoryIndex, final Material material) {
        addToCategory(subCategoryIndex, material, 0);
    }

    /*
     * GETTERS & SETTERS
     */

    public String[] getTypes() {
        return null;
    }

    @Contract(pure = true)
    public final Map<Material, Integer>[] getItems() {
        return items;
    }
}
