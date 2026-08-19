package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;

/**
 * Provides centralized access to all plugin configuration files.
 */
public final class ConfigManager {
    private final MainConfig config;
    private final WeightsConfig weights;

    public ConfigManager(final JavaPlugin plugin) {
        this.config = new MainConfig(plugin);
        this.weights = new WeightsConfig(plugin);
    }

    /**
     * Returns the main plugin configuration.
     *
     * @return the main configuration
     */
    @Contract(pure = true)
    public MainConfig getMainConfig() {
        return config;
    }

    /**
     * Returns the weights configuration.
     *
     * @return the weights configuration
     */
    @Contract(pure = true)
    public WeightsConfig getWeights() {
        return weights;
    }

    public void loadAll() {
        config.load();
        weights.load();
    }

    public void reloadAll() {
        config.reload();
        weights.reload();
    }
}
