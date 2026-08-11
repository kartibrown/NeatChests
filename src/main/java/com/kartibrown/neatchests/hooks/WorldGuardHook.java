package com.kartibrown.neatchests.hooks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class WorldGuardHook implements ProtectionHook {

    private final RegionQuery regionQuery;

    public WorldGuardHook() {
        regionQuery = WorldGuard.getInstance()
                .getPlatform()
                .getRegionContainer()
                .createQuery();
    }


    @Override
    public boolean canAccess(final Player player, final Location location) {
        var wgLocation = BukkitAdapter.adapt(location);
        var localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);

        return regionQuery.testState(
                wgLocation,
                localPlayer,
                Flags.CHEST_ACCESS
        );
    }
}
