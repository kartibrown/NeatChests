package com.kartibrown.neatchests.hooks;

import com.kartibrown.neatchests.logger.LoggerManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class WorldGuardHook implements ProtectionHook {

    private final RegionQuery regionQuery;

    private final LoggerManager logger;

    public WorldGuardHook(final LoggerManager logger) {
        regionQuery = WorldGuard.getInstance()
                .getPlatform()
                .getRegionContainer()
                .createQuery();

        this.logger = logger;
    }


    @Override
    public boolean canAccess(final Player player, final Location location) {
        final var localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        final var platform = WorldGuard.getInstance().getPlatform();

        if (platform.getSessionManager().hasBypass(
                localPlayer,
                BukkitAdapter.adapt(player.getWorld())
        )) {
            return true;
        }

        final var wgLocation = BukkitAdapter.adapt(location);

        // Players with normal build permissions (owners/members) are allowed immediately.
        // Otherwise, allow access only if the region explicitly grants CHEST_ACCESS.
        if (regionQuery.testBuild(wgLocation, localPlayer, Flags.BUILD)) {
            return true;
        }

        return regionQuery.testState(wgLocation, localPlayer, Flags.CHEST_ACCESS);
    }
}
