package com.kartibrown.neatchests.hooks;

import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class ResidenceHook implements ProtectionHook {

    public ResidenceHook() {
    }

    @Override
    public boolean canAccess(final Player player, final Location location) {
        final FlagPermissions perms = FlagPermissions.getPerms(location);

        return perms.playerHas(player, Flags.container, true);
    }
}
