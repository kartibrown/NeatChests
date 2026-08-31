package com.kartibrown.neatchests.hooks;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class GriefPreventionHook implements ProtectionHook {
    @Override
    public boolean canAccess(final Player player, final Block block) {
        final Claim claim = GriefPrevention.instance.dataStore.getClaimAt(
                block.getLocation(), true /*ignore height*/, null
        );
        // if no claim is found at the location then return true
        if (claim == null)
            return true;

        return claim.checkPermission(player, ClaimPermission.Inventory, null) == null;
    }
}
