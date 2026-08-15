package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigManager {
    private final MainConfig config;
    private final WeightsConfig weights;

    public ConfigManager(final JavaPlugin plugin) {
        this.config = new MainConfig(plugin);
        this.weights = new WeightsConfig(plugin);
    }

    public MainConfig getMainConfig() {
        return config;
    }

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
