package com.kartibrown.neatchests.listener;

import com.kartibrown.neatchests.cooldown.CooldownManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public final class PlayerListener implements Listener {
    private final CooldownManager cooldownManager;

    @Contract(pure = true)
    public PlayerListener(final CooldownManager cooldownManager) {
        this.cooldownManager = cooldownManager;
    }

    @EventHandler
    public void onPlayerQuit(final @NonNull PlayerQuitEvent event) {
        cooldownManager.removePlayer(event.getPlayer().getUniqueId());
    }
}
