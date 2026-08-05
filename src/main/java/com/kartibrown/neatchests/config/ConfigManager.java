package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;

public final class ConfigManager {

    private final JavaPlugin plugin;

    @Contract(pure = true)
    public ConfigManager(final JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void load() {
        plugin.saveDefaultConfig();
    }

    public void reload() {
        plugin.reloadConfig();
    }

    public boolean isDebugEnabled() {
        return plugin.getConfig().getBoolean("logging.debug");
    }

    public boolean isStartupEnabled() {
        return plugin.getConfig().getBoolean("logging.startup");
    }

    public boolean isPerformanceEnabled() {
        return plugin.getConfig().getBoolean("logging.performance");
    }
}
