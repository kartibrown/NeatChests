package com.kartibrown.neatchests.config;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.logging.Level;

public final class WeightsConfig extends AbstractConfig implements ConfigFile {
    private boolean created;

    public WeightsConfig(final JavaPlugin plugin) {
        super(plugin, "weights.yml");
    }

    @Override
    public void load() {
        created = false;

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
            created = true;
        }

        fileConfig = YamlConfiguration.loadConfiguration(file);
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

    public void generateDefaults(final Category[] sortingCategories) {
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

    @Contract(pure = true)
    public boolean wasCreated() {
        return created;
    }
}
