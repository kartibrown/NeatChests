package com.kartibrown.neatchests.hooks;

import org.ayosynk.landClaimPlugin.api.LandClaimAPI;
import org.ayosynk.landClaimPlugin.models.ClaimProfile;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class LandsClaimPluginHook implements ProtectionHook {
    @Override
    public boolean canAccess(final Player player, final Block block) {
        final ClaimProfile cp = LandClaimAPI.getInstance().getClaimAt(block.getLocation());

        // if it couldn't find any claim return true
        if (cp == null) return true;

        return LandClaimAPI.getInstance().hasPermission(cp, player.getUniqueId(), "USE_CONTAINERS");
    }
}
