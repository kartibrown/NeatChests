package com.kartibrown.neatchests;

import com.kartibrown.neatchests.listener.ChestStorageListener;
import com.kartibrown.neatchests.sorting.SortingManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NeatChestsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final SortingManager sortingManager = new SortingManager();
        getServer().getPluginManager().registerEvents(new ChestStorageListener(sortingManager)
                , this);

        getLogger().info("NeatChests has been enabled successfully!");
    }

    @Override
    public void onDisable() {
    }
}
