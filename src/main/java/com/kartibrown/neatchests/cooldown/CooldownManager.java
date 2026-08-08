package com.kartibrown.neatchests.cooldown;

import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CooldownManager {
    private final Map<UUID, Long> doubleClickTimes;
    private final Map<UUID, Long> commandCooldowns;

    public CooldownManager() {
        doubleClickTimes = new HashMap<>();
        commandCooldowns = new HashMap<>();
    }

    public boolean isDoubleClick(final @NonNull Player player) {
        final UUID uuid = player.getUniqueId();
        final long currentTime = System.currentTimeMillis();
        final long lastTime = doubleClickTimes.getOrDefault(uuid, 0L);

        if (currentTime - lastTime < 250L) {
            doubleClickTimes.put(uuid, 0L);
            return true;
        }

        doubleClickTimes.put(uuid, currentTime);
        return false;
    }

    public boolean hasCommandCooldown(final @NonNull Player player) {
        /*
         * Could add in future to check for permission to ignore cooldowns
         * for players with permissions but IDK if you really need it
         */
        return hasCooldown(commandCooldowns, player, 250L);
    }

    private boolean hasCooldown(
            final @NonNull Map<UUID, Long> cooldowns,
            final @NonNull Player player,
            final long cooldownMillis) {
        final UUID uuid = player.getUniqueId();
        final long now = System.currentTimeMillis();

        final long lastUse = cooldowns.getOrDefault(uuid, 0L);

        if (now - lastUse < cooldownMillis) {
            return true;
        }

        cooldowns.put(uuid, now);
        return false;
    }

    public void removePlayer(final UUID uuid) {
        doubleClickTimes.remove(uuid);
        commandCooldowns.remove(uuid);
    }
}
