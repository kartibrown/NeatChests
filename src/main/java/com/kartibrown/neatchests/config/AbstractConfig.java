package com.kartibrown.neatchests.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class AbstractConfig {
    protected final JavaPlugin plugin;

    protected final String fileName;

    protected final File file;
    protected FileConfiguration fileConfig;

    public  AbstractConfig(final JavaPlugin plugin) {
        this.plugin = plugin;
        fileName = "config.yml"; // Standard config

        file = new File(plugin.getDataFolder(), fileName);
    }

    public AbstractConfig(final JavaPlugin plugin,  final String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;

        file = new File(plugin.getDataFolder(), fileName);
    }
}
