package com.kartibrown.neatchests.hooks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface ProtectionHook {

    boolean canAccess(final Player player, final Block block);
}
