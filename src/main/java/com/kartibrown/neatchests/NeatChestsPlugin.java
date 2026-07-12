package com.kartibrown.neatchests;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class NeatChestsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final SortingManager sortingManager = new SortingManager();

        getServer().getPluginManager().registerEvents(new ChestStorageListener(sortingManager)
                , this);
    }

    @Override
    public void onDisable() {

    }
}
