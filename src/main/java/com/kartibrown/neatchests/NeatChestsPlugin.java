package com.kartibrown.neatchests;

import com.kartibrown.neatchests.commands.CommandsManager;
import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.listener.PlayerListener;
import com.kartibrown.neatchests.logger.LoggerManager;
import com.kartibrown.neatchests.listener.ChestStorageListener;
import com.kartibrown.neatchests.sorting.SortingManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class NeatChestsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config
        final ConfigManager configManager = new ConfigManager(this);
        configManager.load();

        // Logger
        final LoggerManager loggerManager = new LoggerManager(this, configManager);

        // Sorter
        final SortingManager sortingManager = new SortingManager(configManager, loggerManager);

        // Commands
        final String version = getPluginMeta().getVersion();
        final CommandsManager commandsManager = new CommandsManager(
                configManager,
                sortingManager,
                loggerManager,
                version);

        registerCommands(commandsManager);

        // Register Listener
        final ChestStorageListener csl = new ChestStorageListener(configManager, sortingManager);
        final PlayerListener pl = new PlayerListener(csl.getDoubleClickTracker());

        getServer().getPluginManager().registerEvents(csl, this);

        getServer().getPluginManager().registerEvents(pl, this);

        loggerManager.info("NeatChests has been enabled successfully!");
    }

    private void registerCommands(final CommandsManager commandsManager) {
        final String[] commandNames = {
                "neatchests",
                "nc"
        };

        getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                event -> {
                    for (final String commandName : commandNames) {
                        event.registrar().register(
                                commandsManager.createCommandTree(commandName).build()
                        );
                    }
                }
        );
    }

    @Override
    public void onDisable() {
    }
}
