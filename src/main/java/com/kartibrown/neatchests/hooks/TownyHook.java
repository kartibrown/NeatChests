package com.kartibrown.neatchests.hooks;

import com.palmergames.bukkit.towny.object.PlayerCache;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public final class TownyHook implements ProtectionHook {
    public TownyHook() {
    }

    @Override
    public boolean canAccess(final Player player, final @NonNull Block block) {
        return PlayerCacheUtil.getCachePermission(
                player,
                block.getLocation(),
                block.getType(),
                TownyPermission.ActionType.ITEM_USE
        );
    }
}
