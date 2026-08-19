package com.kartibrown.neatchests.sorting;

import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.config.WeightMode;
import com.kartibrown.neatchests.logger.LoggerManager;
import com.kartibrown.neatchests.sorting.category.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Creates and initializes all sorting categories,
 * assigns their default weights, and prepares them
 * for item lookup during sorting.
 */
public final class CategoryManager {
    private static final int CATEGORY_SPACING = 2000;

    private final Category[] categories;

    /**
     * Creates a new category manager and initializes all categories.
     *
     * @param configManager the config manager
     * @param logger        the logger manager
     * @throws IllegalStateException if the number of {@link Material}
     *                               values exceeds the configured {@code CATEGORY_SPACING}, making
     *                               category weight ranges overlap.
     */
    public CategoryManager(final ConfigManager configManager, final LoggerManager logger) {
        // Throw exception if category spacing is too small
        if (Material.values().length >= CATEGORY_SPACING) {
            throw new IllegalStateException(
                    "CATEGORY_SPACING (" + CATEGORY_SPACING +
                            ") must be larger than the number of materials (" +
                            Material.values().length + ")."
            );
        }

        // Init LoggerManager
        if (configManager.getMainConfig().isStartupEnabled()) {
            logger.info("Initializing categories...");
        }

        // For debug
        final long start = System.nanoTime();

        // Fallback class
        final Misc misc = new Misc();

        categories = new Category[]{
                new Valuables(),
                new Equipment(),
                new Redstone(),
                new Food(),
                new Forestry(),
                new Template(),
                misc
        };

        // Get mode from the main config
        final WeightMode weightMode =
                configManager.getMainConfig().getWeightsMode();

        // Load configured weights from weights.yml
        final boolean loadedWeights = configManager.getWeights().loadWeights(
                categories, weightMode
        );

        // Assign default category weights when no configuration was loaded.

        int baseWeight = 1_000_000;

        for (final Category category : categories) {

            // Set weight
            if (!loadedWeights) {
                category.setBaseWeight(baseWeight);
                baseWeight -= CATEGORY_SPACING;
            }

            // Initialize the materials of each category if needed

            // if weight mode is ADVANCED the config inits the category weights
            if (!loadedWeights && weightMode == WeightMode.SIMPLE) {
                category.initialize();
            }
        }

        final Material[] materials = Material.values();
        // sort the materials in alphabetical order
        Arrays.sort(materials, Comparator.comparing(Material::name));

        final int totalMaterials = materials.length;
        int registeredMaterials = 0;

        for (int materialIndex = 0; materialIndex < totalMaterials; materialIndex++) {
            final Material material = materials[materialIndex];

            if (material.isAir() || material.isLegacy()) {
                continue;
            }

            // Get the biggest number to character A first
            final int alphabeticalWeight = totalMaterials - materialIndex;
            boolean added = false;

            // Misc is fallback and doesn't need testing here
            for (int categoryIndex = 0;
                 categoryIndex < categories.length - 1;
                 categoryIndex++) {
                final Category category = categories[categoryIndex];

                // if weight config mode is advanced and category has automatic registration
                // it will not call the containsOrRegister method
                if (weightMode == WeightMode.ADVANCED
                        && category.hasAutomaticRegistration()) {
                    continue;
                }

                // Adds or checks that a material has been added
                if (category.containsOrRegister(material)) {
                    added = true;
                    registeredMaterials++;
                    break;
                }
            }

            // FALLBACK
            if (!added) {
                misc.addFallback(material, alphabeticalWeight);
                registeredMaterials++;
            }
        }

        // Log registered materials and time it took
        if (configManager.getMainConfig().isStartupEnabled()) {
            logger.info("Registered " + registeredMaterials + " valid materials.");
            logger.logElapsedTime(start, "Initialization");
        }
    }

    /**
     * Returns the category that the material passed is located in, also adds
     * the material to the category if it needs to add it
     *
     * @param material The material to find the category for
     * @return Returns the category that the material is in
     */
    @Contract(pure = true)
    private @Nullable Category findCategoryFor(final Material material) {
        for (final Category category : categories) {
            if (category.contains(material)) {
                return category;
            }
        }
        return null;
    }

    /**
     * Gets the weight safely from the item, will return -1 if null
     *
     * @param item The item to get the weight from
     * @return Returns -1 if item is null or if Category is null or if the Integer
     * to get the weight is null
     */
    public int getWeightSafely(final @Nullable ItemStack item) {
        if (item == null) {
            return -1;
        }

        final Category category = findCategoryFor(item.getType());
        if (category == null) {
            return -1;
        }

        final Integer weight = category.getWeightFor(item.getType());

        return weight != null ? weight : -1;
    }

    /*
     * GETTERS & SETTERS
     */

    /**
     * Returns a copy of all categories
     *
     * @return returns a new copy of all the categories
     */
    @Contract(pure = true)
    public Category[] getCategories() {
        return categories.clone();
    }
}
