package com.kartibrown.neatchests.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.io.File;

public abstract class AbstractConfig {
    protected final JavaPlugin plugin;

    protected final String fileName;

    protected final File file;
    protected FileConfiguration fileConfig;

    public  AbstractConfig(final @NonNull JavaPlugin plugin) {
        this.plugin = plugin;
        fileName = "config.yml"; // Standard config

        file = new File(plugin.getDataFolder(), fileName);
    }

    public AbstractConfig(final @NonNull JavaPlugin plugin, final String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;

        file = new File(plugin.getDataFolder(), fileName);
    }
}
