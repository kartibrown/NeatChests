package com.kartibrown.neatchests.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.util.Locale;
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

    /**
     * Logs how long an operation took.
     *
     * @param startTime The timestamp returned by {@link System#nanoTime()} before the operation started.
     * @param prefix    The message shown before the elapsed time.<br>
     *                  Example: "Chest sorting" becomes
     *                  "Chest sorting took 0.352 ms."
     */
    public void logElapsedTime(final long startTime, final String prefix) {
        final long elapsed = System.nanoTime() - startTime;

        logger.info(String.format(
                Locale.US, // makes it always show 20.0 ms, instead of 20,0 ms
                "%s took %.3f ms.",
                prefix,
                elapsed / 1_000_000.0
        ));
    }
}
