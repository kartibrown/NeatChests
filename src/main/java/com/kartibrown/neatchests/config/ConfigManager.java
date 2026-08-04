package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigManager {

    private final JavaPlugin plugin;

    public ConfigManager(final JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void load() {
        plugin.saveDefaultConfig();
    }

    public boolean isDebugEnabled() {
        return plugin.getConfig().getBoolean("logging.debug");
    }

    public boolean isVerboseEnabled() {
        return plugin.getConfig().getBoolean("logging.verbose");
    }
}
