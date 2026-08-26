package com.kartibrown.neatchests.hooks;

import com.kartibrown.neatchests.logger.LoggerManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ProtectionHookManager implements ProtectionHook {
    private final LoggerManager loggerManager;

    private final List<ProtectionHook> protectionHooks;

    public ProtectionHookManager(final LoggerManager loggerManager) {
        this.loggerManager = loggerManager;

        protectionHooks = new ArrayList<>();
        addHook("WorldGuard", WorldGuardHook::new);
        addHook("Residence", ResidenceHook::new);
        addHook("Towny", TownyHook::new);
    }

    private void addHook(final String pluginName,
                         final Supplier<ProtectionHook> supplier) {

        if (!Bukkit.getPluginManager().isPluginEnabled(pluginName)) {
            return;
        }

        loggerManager.info("Hooked into " + pluginName + ".");

        if (pluginName.equals("Towny")) {
            loggerManager.info("The '/nc' alias may conflict with Towny's nation chat.");
            loggerManager.info("Consider changing 'commands.aliases' in config.yml.");
        }

        protectionHooks.add(supplier.get());
    }

    @Override
    public boolean canAccess(final Player player, final Block block) {

        for (final ProtectionHook protectionHook : protectionHooks) {
            if (!protectionHook.canAccess(player, block)) {
                return false;
            }
        }

        return true;
    }
}
