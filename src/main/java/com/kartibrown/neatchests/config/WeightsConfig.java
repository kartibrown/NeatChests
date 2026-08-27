package com.kartibrown.neatchests.config;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

/**
 * Handles loading, saving, and accessing values stored in {@code weights.yml}.<br>
 * Also generates the file with default values when needed.
 */
public final class WeightsConfig extends AbstractConfig implements ConfigFile {
    private static final int CURRENT_VERSION = 1;

    private static final List<String> BLANK_LINE = Collections.singletonList(null);

    private boolean generated;

    public WeightsConfig(final JavaPlugin plugin) {
        super(plugin, "weights.yml");
    }

    @Override
    public void load() {
        generated = !file.exists();

        fileConfig = new YamlConfiguration();

        if (!generated) {
            fileConfig = YamlConfiguration.loadConfiguration(file);
        }
    }

    @Override
    public void reload() {
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void save() {
        try {
            fileConfig.save(file);
        } catch (final IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config file " + fileName, e);
        }
    }

    /**
     * Applies the weights stored in {@code weights.yml} to the given categories.
     *
     * @param sortingCategories the categories to update
     * @return {@code true} if the weights were successfully applied,
     * otherwise {@code false}
     */
    public boolean loadWeights(final Category[] sortingCategories,
                               final WeightMode weightMode,
                               final SortingMode sortingMode) {
        if (needsRegeneration(weightMode, sortingMode)) {
            return false;
        }

        return switch (weightMode) {
            case SIMPLE -> applySimpleWeights(sortingCategories);
            case ADVANCED -> applyAdvancedWeights(sortingCategories);
        };
    }

    private boolean applySimpleWeights(final Category[] sortingCategories) {
        final ConfigurationSection categoriesSection =
                fileConfig.getConfigurationSection("categories");

        if (categoriesSection == null) {
            return false;
        }

        for (final Category category : sortingCategories) {
            final int weight = categoriesSection.getInt(category.name().toLowerCase(), -1);
            if (weight < 0) {
                plugin.getLogger().log(Level.WARNING, "Invalid weight for category '"
                        + category.name() + "' in weights.yml");
                return false;
            }

            category.setBaseWeight(weight);
        }
        return true;
    }

    private boolean applyAdvancedWeights(final Category[] sortingCategories) {
        final ConfigurationSection categoriesSection =
                fileConfig.getConfigurationSection("categories");

        if (categoriesSection == null) {
            return false;
        }

        for (final Category category : sortingCategories) {
            final ConfigurationSection categorySection =
                    categoriesSection.getConfigurationSection(category.name().toLowerCase());
            if (categorySection == null) {
                plugin.getLogger().warning(
                        "Could not load configuration for category '"
                                + category.name() + "´ in weights.yml."
                );
                return false;
            }

            final ConfigurationSection itemsSection =
                    categorySection.getConfigurationSection("items");

            if (itemsSection == null) {
                plugin.getLogger().warning("Could not load items in configuration for category '"
                        + category.name() + "' in weights.yml.");
                return false;
            }

            // Adds the items and weights to the categories
            final Map<String, Object> items = itemsSection.getValues(false);
            for (final Map.Entry<String, Object> entry : items.entrySet()) {
                category.add(Material.valueOf(entry.getKey()), (Integer) entry.getValue());
            }
        }
        return true;
    }

    /**
     * Returns the weight mode stored in {@code weights.yml}.
     *
     * @return the stored weight mode from {@code metadata.mode}
     */
    public WeightMode getStoredWeightMode() {
        return WeightMode.fromString(fileConfig.getString("metadata.weight-mode"));
    }

    /**
     * Returns the sorting mode stored in {@code weights.yml}.
     *
     * @return the stored sorting mode from {@code metadata.mode}
     */
    public SortingMode getStoredSortingMode() {
        return SortingMode.fromString(fileConfig.getString("metadata.sorting-mode"));
    }

    /**
     * Returns the configuration version stored in {@code weights.yml}.
     *
     * @return the stored configuration version
     */
    public int getStoredVersion() {
        return fileConfig.getInt("metadata.version");
    }

    /**
     * Regenerates the default contents of {@code weights.yml} if the configuration
     * format has changed or the file does not yet exist.
     *
     * @param sortingCategories the sorting categories, it's used to write all the categories in
     *                          the {@code weights.yml}
     * @param weightMode        the weight mode currently configured in {@code weights.yml}
     * @param sortingMode       the sorting mode currently configured in {@code weights.yml}
     */
    public void generateDefaultsIfNeeded(final Category[] sortingCategories,
                                         final WeightMode weightMode,
                                         final SortingMode sortingMode) {
        if (!needsRegeneration(weightMode, sortingMode)) {
            return;
        }

        plugin.getLogger().info("Regenerating weights.yml because the format has changed.");

        fileConfig.options().setHeader(
                List.of(
                        "NeatChests weights configuration.",
                        "",
                        "In SIMPLE mode, only category weights are configurable.",
                        "In ADVANCED mode, both categories and individual items can be configured.",
                        "",
                        "Higher weights are sorted first.",
                        "",
                        "Items are listed alphabetically.",
                        "The weight determines their sort priority."
                )
        );

        ConfigurationSection metadata = fileConfig.createSection("metadata");
        fileConfig.setComments(
                "metadata",
                List.of(
                        "Internal metadata used by NeatChests.",
                        "Modifying these values may cause the file to be regenerated."
                )
        );
        metadata.set("version", CURRENT_VERSION);
        metadata.set("weight-mode", weightMode.name().toLowerCase(Locale.ROOT));
        metadata.set("sorting-mode", sortingMode.name().toLowerCase(Locale.ROOT));

        switch (weightMode) {
            case SIMPLE -> generateSimple(sortingCategories);
            case ADVANCED -> generateAdvanced(sortingCategories);
        }

        fileConfig.setComments("categories", BLANK_LINE);

        generated = true;
        save();
    }

    private void generateSimple(final Category @NonNull [] sortingCategories) {
        // Generates the simple mode config
        final ConfigurationSection categoriesSection = fileConfig.createSection("categories");

        for (final Category category : sortingCategories) {
            categoriesSection.set(category.name().toLowerCase(), category.getStartWeight());
        }
    }

    private void generateAdvanced(final Category @NonNull [] sortingCategories) {
        // Generates the advanced mode config
        final ConfigurationSection categoriesSection = fileConfig.createSection("categories");

        for (final Category category : sortingCategories) {

            final ConfigurationSection categorySection =
                    categoriesSection.createSection(category.name().toLowerCase());

            categorySection.set("weight", category.getStartWeight());
            categorySection.createSection("items", category.getSubCategories());
        }
    }

    /*
     * GETTERS & SETTERS
     */

    /**
     * Determines whether {@code weights.yml} should be regenerated.
     *
     * @param currentWeightMode  The current weight mode stored in the config file
     * @param currentSortingMode the current sorting mode stored in the config file
     * @return {@code true} if config needs regeneration
     */
    private boolean needsRegeneration(final WeightMode currentWeightMode,
                                      final SortingMode currentSortingMode) {
        return wasCreated()
                || getStoredWeightMode() != currentWeightMode
                || getStoredSortingMode() != currentSortingMode;
    }

    /**
     * Returns whether {@code weights.yml} was created during startup.
     *
     * @return {@code true} if the file was created, {@code false} if
     * the file already existed
     */
    @Contract(pure = true)
    private boolean wasCreated() {
        return generated;
    }
}
