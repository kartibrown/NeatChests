package com.kartibrown.neatchests;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class ChestStorageListener implements Listener {
    final SortingManager sortingManager;

    public ChestStorageListener(final SortingManager sortingManager) {
        this.sortingManager = sortingManager;
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        // Always get the top inventory to check if a chest is open
        final Inventory topInventory = event.getInventory();

        if (topInventory.getType() == InventoryType.CHEST) {
            handleChestClick(event, topInventory);
        }
    }

    private void handleChestClick(final InventoryClickEvent event, final Inventory chestInventory) {
        // 1. Check if they clicked the actual chest
        if (event.getClickedInventory() == null || !event.getClickedInventory().equals(chestInventory)) {
            return;
        }

        // 2. Check if they double-clicked (using Paper's ClickType)
        if (event.getClick() == ClickType.DOUBLE_CLICK) {

            // 3. Cancel the default double-click behavior (gathering identical items)
            event.setCancelled(true);

            // 4. Get all items currently in the chest

            final ItemStack[] sortedItems = sortingManager.sortChestItems(
                    chestInventory.getContents()
            );

            //sortChestContents(removeEmptySlots(items));

            // 5. Put the sorted items back into the chest
            chestInventory.clear();
            chestInventory.setContents(sortedItems);

            event.getWhoClicked().sendMessage("Chest sorted!");
        }
    }
}

