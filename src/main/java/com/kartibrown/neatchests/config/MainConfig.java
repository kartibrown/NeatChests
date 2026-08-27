package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.util.List;

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
        final WeightMode wm1 = getWeightsMode();
        final SortingMode sm1 = getSortingMode();
        fileConfig = plugin.getConfig();
        final WeightMode wm2 = getWeightsMode();
        final SortingMode sm2 = getSortingMode();

        if (wm1 != wm2 || sm1 != sm2) {
            plugin.getLogger().warning("Changes will take effect after restart...");
        }
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

    public SortingMode getSortingMode() {
        return SortingMode.fromString(fileConfig.getString("sorting.mode"));
    }

    public @NonNull List<String> getAliases() {
        return fileConfig.getStringList("commands.aliases");
    }
}
