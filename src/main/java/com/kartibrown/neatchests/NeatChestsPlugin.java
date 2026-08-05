package com.kartibrown.neatchests;

import com.kartibrown.neatchests.commands.CommandsManager;
import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.config.LoggerManager;
import com.kartibrown.neatchests.listener.ChestStorageListener;
import com.kartibrown.neatchests.sorting.SortingManager;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class NeatChestsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config
        final ConfigManager configManager = new ConfigManager(this);
        configManager.load();

        // Logger
        final LoggerManager loggerManager = new LoggerManager(this, configManager);

        // Commands
        final String version = getPluginMeta().getVersion();
        final CommandsManager commandsManager = new CommandsManager(
                configManager,
                version);
        final LiteralCommandNode<CommandSourceStack> buildCommand =
                commandsManager.createCommandTree().build();

        // Add commands to plugin
        this.getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                commands ->
                    commands.registrar().register(buildCommand)
                );

        // Sorting
        final SortingManager sortingManager = new SortingManager(configManager, loggerManager);

        getServer().getPluginManager().registerEvents(new ChestStorageListener(sortingManager)
                , this);

        loggerManager.info("NeatChests has been enabled successfully!");
    }

    @Override
    public void onDisable() {
    }
}
