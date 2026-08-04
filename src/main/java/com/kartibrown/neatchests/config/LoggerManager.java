package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.util.logging.Logger;

public final class LoggerManager {
    private final Logger logger;
    private final ConfigManager configManager;

    public LoggerManager(final @NonNull JavaPlugin plugin, final ConfigManager configManager) {
        this.logger = plugin.getLogger();
        this.configManager = configManager;

        debug("Debug logging enabled.");
    }

    public void info(final String message) {
        logger.info(message);
    }

    public void warning(final String message) {
        logger.warning(message);
    }

    public void severe(final String message) {
        logger.severe(message);
    }

    public void debug(final String message) {
        if (configManager.isDebugEnabled()) {
            logger.info("[DEBUG] " + message);
        }
    }

    public void verbose(final String message) {
        if (configManager.isDebugEnabled() && configManager.isVerboseEnabled()) {
            logger.info("[VERBOSE] " + message);
        }
    }
}
