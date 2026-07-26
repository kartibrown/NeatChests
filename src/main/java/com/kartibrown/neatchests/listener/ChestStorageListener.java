package com.kartibrown.neatchests.listener;

import com.kartibrown.neatchests.sorting.SortingManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ChestStorageListener implements Listener {
    final SortingManager sortingManager;

    private final Map<UUID, Long> lastClickTime;

    @Contract(pure = true)
    public ChestStorageListener(final SortingManager sortingManager) {
        this.sortingManager = sortingManager;

        lastClickTime = new HashMap<>();
    }

    @EventHandler
    public void onClick(final @NonNull InventoryClickEvent event) {
        // ignore if cursor is not empty
        final ItemStack cursor = event.getCursor();
        if (!cursor.isEmpty()) {
            return;
        }

        final Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null) {
            return;
        }

        final InventoryType type = clickedInventory.getType();

        if (type == InventoryType.PLAYER
                || type == InventoryType.CHEST
                || type == InventoryType.SHULKER_BOX
                || type == InventoryType.ENDER_CHEST) {
            handleInventoryClick(event, clickedInventory);
        }
    }

    private void handleInventoryClick(
            final @NonNull InventoryClickEvent event,
            final @NonNull Inventory inventory
    ) {
        // Return if it's not a left-click so it doesn't trigger when left- and then right-clicking
        if (event.getClick() != ClickType.LEFT) {
            return;
        }

        final Player player = (Player) event.getWhoClicked();

        // return if it's not a double click
        if (!isDoubleClick(player)) {
            return;
        }

        // Cancel the default click/double-click behavior
        event.setCancelled(true);

        final ItemStack[] contents = inventory.getStorageContents();

        if (inventory.getType() == InventoryType.PLAYER) {
            final int hotBarSize = 9;

            // ignores the hotbar in the players inventory
            final ItemStack[] sortableContents = Arrays.copyOfRange(
                    contents,
                    hotBarSize,
                    contents.length);

            final ItemStack[] sortedContents = sortingManager.sortChestItems(sortableContents);

            // keep the original to keep the hotbar from changing
            final ItemStack[] finalContents = contents.clone();

            // Only empty the inventory above the hotbar
            Arrays.fill(finalContents, hotBarSize, finalContents.length, null);

            // put back the items after the hotbar
            System.arraycopy(
                    sortedContents,
                    0,
                    finalContents,
                    hotBarSize,
                    sortedContents.length
            );

            inventory.setStorageContents(finalContents);
        } else {

            final ItemStack[] sortedItems =
                    sortingManager.sortChestItems(contents);

            final ItemStack[] finalContents =
                    new ItemStack[contents.length];

            // copy the
            System.arraycopy(
                    sortedItems,
                    0,
                    finalContents,
                    0,
                    sortedItems.length
            );

            inventory.setStorageContents(finalContents);
        }

        player.sendMessage(
                inventory.getType() == InventoryType.PLAYER
                        ? "§aInventory sorted!"
                        : "§aChest sorted!"
        );
    }

    private boolean isDoubleClick(final @NonNull Player player) {
        final UUID uuid = player.getUniqueId();
        final long currentTime = System.currentTimeMillis();
        final long lastTime = lastClickTime.getOrDefault(uuid, 0L);

        if (currentTime - lastTime < 250L) {
            lastClickTime.put(uuid, 0L);
            return true;
        }

        lastClickTime.put(uuid, currentTime);
        return false;
    }
}

