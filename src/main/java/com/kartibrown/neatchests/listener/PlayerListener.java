package com.kartibrown.neatchests.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.UUID;

public final class PlayerListener implements Listener {
    private final Map<UUID, Long> lastClickTime;

    @Contract(pure = true)
    public PlayerListener(final Map<UUID, Long> lastClickTime) {
        this.lastClickTime = lastClickTime;
    }

    @EventHandler
    public void onPlayerQuit(final @NonNull PlayerQuitEvent event) {
        lastClickTime.remove(event.getPlayer().getUniqueId());
    }
}
