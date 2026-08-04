package com.kartibrown.neatchests;

import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.config.LoggerManager;
import com.kartibrown.neatchests.listener.ChestStorageListener;
import com.kartibrown.neatchests.sorting.SortingManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NeatChestsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config
        final ConfigManager config = new ConfigManager(this);
        config.load();

        // Logger
        final LoggerManager logger = new LoggerManager(this, config);

        // Sorting
        final SortingManager sortingManager = new SortingManager(logger);

        getServer().getPluginManager().registerEvents(new ChestStorageListener(sortingManager)
                , this);

        logger.info("NeatChests has been enabled successfully!");
    }

    @Override
    public void onDisable() {
    }
}
