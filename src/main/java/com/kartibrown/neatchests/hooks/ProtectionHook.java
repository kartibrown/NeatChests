package com.kartibrown.neatchests.hooks;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ProtectionHook {

    boolean canAccess(final Player player, final Location location);
}
