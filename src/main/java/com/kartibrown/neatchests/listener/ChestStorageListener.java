package com.kartibrown.neatchests.listener;

import com.kartibrown.neatchests.commands.CommandsManager;
import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.cooldown.CooldownManager;
import com.kartibrown.neatchests.sorting.SortingManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public final class ChestStorageListener implements Listener {
    final ConfigManager configManager;
    final SortingManager sortingManager;
    final CooldownManager cooldownManager;

    @Contract(pure = true)
    public ChestStorageListener(final ConfigManager configManager,
                                final SortingManager sortingManager,
                                final CooldownManager cooldownManager) {
        this.configManager = configManager;
        this.sortingManager = sortingManager;
        this.cooldownManager = cooldownManager;
    }

    @EventHandler
    public void onClick(final @NonNull InventoryClickEvent event) {

        // ignore if double-click in config is set to false or if cursor is not empty
        if (!configManager.isDoubleClickSortEnabled() || !event.getCursor().isEmpty()) {
            return;
        }

        final Player player = (Player) event.getWhoClicked();
        // ignore if player doesn't have permission
        if (!player.hasPermission(CommandsManager.SORT_PERMISSION)) {
            return;
        }

        final Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null) {
            return;
        }

        final InventoryType type = clickedInventory.getType();

        // Handle the inventory if it's as follows
        if (type == InventoryType.PLAYER
                || type == InventoryType.CHEST
                || type == InventoryType.SHULKER_BOX
                || type == InventoryType.ENDER_CHEST) {
            handleInventoryClick(event, clickedInventory, player);
        }
    }

    private void handleInventoryClick(
            final @NonNull InventoryClickEvent event,
            final @NonNull Inventory inventory,
            final @NonNull Player player
    ) {
        // Return if it's not a left-click so it doesn't trigger when left- and then right-clicking
        if (event.getClick() != ClickType.LEFT) {
            return;
        }

        // return if it's not a double click
        if (!cooldownManager.isDoubleClick(player)) {
            return;
        }

        // Cancel the default click/double-click behavior
        event.setCancelled(true);

        // sort the inventory
        sortingManager.sortInventory(inventory);

        player.sendMessage(
                inventory.getType() == InventoryType.PLAYER
                        ? "§aInventory sorted!"
                        : "§aChest sorted!"
        );
    }
}

