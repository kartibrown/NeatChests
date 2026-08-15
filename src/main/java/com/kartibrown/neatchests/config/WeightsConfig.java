package com.kartibrown.neatchests.config;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

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

    public WeightMode getStoredMode() {
        return WeightMode.fromString(fileConfig.getString("metadata.mode"));
    }

    public int getStoredVersion() {
        return fileConfig.getInt("metadata.version");
    }

    public void generateDefaultsIfNeeded(final Category[] sortingCategories, final WeightMode currentMode) {
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
                        "Higher weights are sorted first."
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
        final ConfigurationSection categoriesSection = fileConfig.createSection("categories");

        for (final Category category : sortingCategories) {
            categoriesSection.set(category.name().toLowerCase(), category.getStartWeight());
        }
    }

    private void generateAdvanced(final Category @NonNull [] sortingCategories) {
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

    private boolean needsRegeneration(final WeightMode currentMode) {
        return wasCreated()
                || getStoredMode() != currentMode;
    }

    @Contract(pure = true)
    private boolean wasCreated() {
        return created;
    }
}
