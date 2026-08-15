package com.kartibrown.neatchests.sorting;

import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public abstract class Category {
    protected String name;

    private int startWeight;
    protected int baseWeight;

    protected final Map<Material, Integer>[] subCategories;

    @SuppressWarnings("unchecked")
    public Category(final int numberOfSubCategories) {
        name = getClass().getSimpleName();

        startWeight = -1;

        subCategories = (EnumMap<Material, Integer>[]) new EnumMap[numberOfSubCategories];

        for (int i = 0; i < numberOfSubCategories; i++) {
            subCategories[i] = new EnumMap<>(Material.class);
        }
    }

    /**
     * Initializes the category after its base weight has been assigned.
     * Categories that do not require initialization may use the default implementation.
     */
    public void initialize() {
        initializeStartWeight(baseWeight);
    }

    /**
     * Returns whether this category contains the given material.
     * Categories with dynamic registration may register the material
     * during this call.
     *
     * @param material the material to check or register
     * @return {@code true} if the material belongs to this category
     */
    public abstract boolean containsOrRegister(final Material material);

    /**
     * Most likely only for the FallBack class!<br>
     * Adds the material to the first sub-category with a weight
     *
     * @param material The material to add
     * @param weight   The weight to add
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
        subCategories[subCategoryIndex].put(material, weight);
    }

    /**
     * Adds a material to the desired sub category and automates the weight.<br>
     * Can be used without setBaseWeight() but the weight will be set to (MAX_WEIGHT + MIN_WEIGHT) / 2
     *
     * @param subCategoryIndex The desired sub category
     * @param material         The item to add
     */
    protected final void addWithAutoWeight(final int subCategoryIndex, final Material material) {
        subCategories[subCategoryIndex].put(material, baseWeight);

        baseWeight--;
    }

    /*
     * GETTERS & SETTERS
     */

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
        for (final Map<Material, Integer> subCategoryMap : subCategories) {
            if (subCategoryMap.containsKey(material)) {
                return true;
            }
        }
        return false;
    }

    @Contract(mutates = "this")
    protected final void initializeStartWeight(final int weight) {
        if (startWeight == -1) {
            startWeight = weight;
        }
    }

    /*
     * GETTERS & SETTERS
     */

    /**
     * Sets the base weight for the category
     *
     * @param weight The weight
     */
    @Contract(mutates = "this")
    protected final void setBaseWeight(final int weight) {
        baseWeight = weight;
    }

    /**
     *
     * @return Returns the category's starting weight
     */
    @Contract(pure = true)
    public final int getStartWeight() {
        return startWeight;
    }

    /**
     *
     * @return Returns the name of the category
     */
    @Contract(pure = true)
    public final String name() {
        return name;
    }

    /**
     *
     * @return Returns all sub categories in one map
     */
    public final @NonNull Map<Material, Integer> getSubCategories() {
        final Map<Material, Integer> mapOfAll = new EnumMap<>(Material.class);

        for(final Map<Material, Integer> subCat : this.subCategories){
            mapOfAll.putAll(subCat);
        }

        return mapOfAll;
    }
}
