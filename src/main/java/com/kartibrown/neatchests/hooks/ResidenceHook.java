package com.kartibrown.neatchests.hooks;

import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.containers.ResAdmin;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;

public final class ResidenceHook implements ProtectionHook {

    @Contract(pure = true)
    public ResidenceHook() {
    }

    @Override
    public boolean canAccess(final Player player, final Block block) {
        if (ResAdmin.isResAdmin(player)) {
            return true;
        }

        final FlagPermissions perms = FlagPermissions.getPerms(block.getLocation());

        return perms.playerHas(player, Flags.container, true);
    }
}
