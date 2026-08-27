package com.kartibrown.neatchests.hooks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface ProtectionHook {

    /**
     * Checks whether the player may access the inventory.
     *
     * @param player The player attempting to access the inventory.
     * @param block  The block being accessed.
     * @return {@code true} if the player has access, otherwise {@code false}.
     */
    boolean canAccess(final Player player, final Block block);
}
