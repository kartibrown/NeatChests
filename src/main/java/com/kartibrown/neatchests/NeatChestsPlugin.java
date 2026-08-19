package com.kartibrown.neatchests;

import com.kartibrown.neatchests.commands.CommandsManager;
import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.config.WeightsConfig;
import com.kartibrown.neatchests.cooldown.CooldownManager;
import com.kartibrown.neatchests.hooks.ProtectionHookManager;
import com.kartibrown.neatchests.listener.PlayerListener;
import com.kartibrown.neatchests.logger.LoggerManager;
import com.kartibrown.neatchests.listener.ChestStorageListener;
import com.kartibrown.neatchests.sorting.Category;
import com.kartibrown.neatchests.sorting.CategoryManager;
import com.kartibrown.neatchests.sorting.SortingManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class NeatChestsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // Config
        final ConfigManager configManager = new ConfigManager(this);
        configManager.loadAll();

        // Logger
        final LoggerManager logger = new LoggerManager(this, configManager);

        // Category
        final CategoryManager categoryManager = new CategoryManager(configManager, logger);

        // Write default to the weights.yml config file
        configManager.getWeights().generateDefaultsIfNeeded(
                categoryManager.getCategories(),
                configManager.getMainConfig().getWeightsMode()
        );

        // Sorting
        final SortingManager sortingManager = new SortingManager(
                configManager,
                logger,
                categoryManager
        );

        // Cooldowns
        final CooldownManager cooldownManager = new CooldownManager();

        // Hooks
        final ProtectionHookManager protectionHookManager = new ProtectionHookManager(logger);

        // Commands
        final String version = getPluginMeta().getVersion();
        final CommandsManager commandsManager = new CommandsManager(
                configManager,
                sortingManager,
                logger,
                protectionHookManager,
                cooldownManager,
                version
        );

        registerCommands(commandsManager);

        // Register Listener
        final ChestStorageListener chestStorageListener = new ChestStorageListener(
                configManager,
                sortingManager,
                cooldownManager
        );
        final PlayerListener playerListener = new PlayerListener(cooldownManager);

        getServer().getPluginManager().registerEvents(chestStorageListener, this);

        getServer().getPluginManager().registerEvents(playerListener, this);

        logger.info("NeatChests has been enabled successfully!");
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
