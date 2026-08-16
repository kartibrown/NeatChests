package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles loading, saving, and accessing values stored in {@code config.yml}.
 */
public final class MainConfig extends AbstractConfig implements ConfigFile {
    public MainConfig(final JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        // Copies and saves the config.yml file from resources
        plugin.saveDefaultConfig();

        fileConfig = plugin.getConfig();
        fileConfig.options().copyDefaults(true);
        plugin.saveConfig();
    }

    @Override
    public void reload() {
        plugin.reloadConfig();
        fileConfig = plugin.getConfig();
    }

    @Override
    public void save() {
        plugin.saveConfig();
    }

    public boolean isDebugEnabled() {
        return fileConfig.getBoolean("logging.debug");
    }

    public boolean isStartupEnabled() {
        return fileConfig.getBoolean("logging.startup");
    }

    public boolean isPerformanceEnabled() {
        return fileConfig.getBoolean("logging.performance");
    }

    public boolean isDoubleClickSortEnabled() {
        return fileConfig.getBoolean("sorting.double-click");
    }

    public boolean isCommandSortEnabled() {
        return fileConfig.getBoolean("sorting.command-sort");
    }

    public WeightMode getWeightsMode() {
        return WeightMode.fromString(fileConfig.getString("weights.mode"));
    }
}
