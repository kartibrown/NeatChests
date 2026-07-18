package com.kartibrown.neatchests;

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

import java.util.HashMap;
import java.util.UUID;

public final class ChestStorageListener implements Listener {
    final SortingManager sortingManager;

    private final HashMap<UUID, Long> lastClickTime;

    @Contract(pure = true)
    public ChestStorageListener(final SortingManager sortingManager) {
        this.sortingManager = sortingManager;

        lastClickTime = new HashMap<>();
    }

    @EventHandler
    public void onClick(final @NonNull InventoryClickEvent event) {
        // Always get the top inventory to check if a chest is open
        final Inventory topInventory = event.getInventory();

        if (topInventory.getType() == InventoryType.CHEST ||
                topInventory.getType() == InventoryType.SHULKER_BOX) {
            handleChestClick(event, topInventory);
        }
    }

    private void handleChestClick(final @NonNull InventoryClickEvent event,
                                  final Inventory chestInventory) {
        // check if they clicked the actual chest
        if (event.getRawSlot() >= event.getInventory().getSize()) {
            return;
        }

        // check if they double-clicked (using Paper's ClickType)
        if (event.getClick() == ClickType.LEFT) {
            final Player player = (Player) event.getWhoClicked();
            final UUID playerUUID = player.getUniqueId();
            final long currentTime = System.currentTimeMillis();

            // get the last time player clicked
            final long lastTime = lastClickTime.getOrDefault(playerUUID, 0L);

            lastClickTime.put(playerUUID, currentTime + 1);

            // if player double-clicked within 250ms
            if (currentTime - lastTime < 250L) {
                // cancel the default double-click behavior (gathering identical items)
                event.setCancelled(true);

                // get all items currently in the chest and sort them
                final ItemStack[] sortedItems = sortingManager.sortChestItems(
                        chestInventory.getContents()
                );

                // create a new array with same length/size as the chest
                final ItemStack[] finalSortedItems = new ItemStack[chestInventory.getSize()];
                // copy the contents of the sorted items
                System.arraycopy(sortedItems, 0, finalSortedItems, 0, sortedItems.length);

                // put the sorted items back into the chest
                chestInventory.setContents(finalSortedItems);

                // Reset timer so a new click registers as a first click
                lastClickTime.put(playerUUID, 0L);

                player.sendMessage("§aChest sorted!");
            }
            else  {
                // save the timer if it wasn't a double click
                lastClickTime.put(playerUUID, currentTime);
            }
        }
    }
}

