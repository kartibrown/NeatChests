package com.kartibrown.neatchests.config;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public final class WeightsConfig extends AbstractConfig implements ConfigFile {

    public WeightsConfig(final JavaPlugin plugin) {
        super(plugin, "weights.yml");
    }

    @Override
    public void load() {
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
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

    public void test(){
        Catego
    }
}
