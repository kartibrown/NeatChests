package com.kartibrown.neatchests.config;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

/**
 * Handles loading, saving, and accessing values stored in {@code weights.yml}.<br>
 * Also generates the file with default values when needed.
 */
public final class WeightsConfig extends AbstractConfig implements ConfigFile {
    private static final int CURRENT_VERSION = 1;

    private static final List<String> BLANK_LINE = Collections.singletonList(null);

    private boolean created;

    public WeightsConfig(final JavaPlugin plugin) {
        super(plugin, "weights.yml");
    }

    @Override
    public void load() {
        created = !file.exists();

        fileConfig = new YamlConfiguration();

        if (!created) {
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
     * Returns the weight mode stored in {@code weights.yml}.
     *
     * @return the stored weight mode from {@code metadata.mode}
     */
    public WeightMode getStoredMode() {
        return WeightMode.fromString(fileConfig.getString("metadata.mode"));
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
     * @param currentMode       the weight mode currently configured in {@code weights.yml}
     */
    public void generateDefaultsIfNeeded(final Category[] sortingCategories,
                                         final WeightMode currentMode) {
        if (!needsRegeneration(currentMode)) {
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
        metadata.set("mode", currentMode.name().toLowerCase(Locale.ROOT));

        switch (currentMode) {
            case SIMPLE -> generateSimple(sortingCategories);
            case ADVANCED -> generateAdvanced(sortingCategories);
        }

        fileConfig.setComments("categories", BLANK_LINE);

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
     * @param currentMode The current mode stored in the config file
     * @return {@code true} if config needs regeneration
     */
    private boolean needsRegeneration(final WeightMode currentMode) {
        return wasCreated()
                || getStoredMode() != currentMode;
    }

    /**
     * Returns whether {@code weights.yml} was created during startup.
     *
     * @return {@code true} if the file was created, {@code false} if
     * the file already existed
     */
    @Contract(pure = true)
    private boolean wasCreated() {
        return created;
    }
}
