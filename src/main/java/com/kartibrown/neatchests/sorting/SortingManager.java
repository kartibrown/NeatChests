package com.kartibrown.neatchests.sorting;

import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.logger.LoggerManager;
import com.kartibrown.neatchests.sorting.category.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.*;

/**
 * Handles inventory sorting based on category and item weights.
 */
public final class SortingManager {

    private static final int HOTBAR_SIZE = 9;

    private final LoggerManager logger;
    private final ConfigManager configManager;
    private final CategoryManager categoryManager;

    @Contract(pure = true)
    public SortingManager(final ConfigManager configManager, final LoggerManager logger, final CategoryManager categoryManager) {
        this.configManager = configManager;
        this.logger = logger;
        this.categoryManager = categoryManager;
    }

    /**
     * Sorts the given inventory using the configured item sorting rules.
     * <p>
     * Player inventories keep the hotbar unchanged, while storage inventories
     * are sorted across all storage slots.
     *
     * @param inventory the inventory to sort
     */
    public void sortInventory(final @NonNull Inventory inventory) {
        final boolean performanceLogging = configManager.getMainConfig().isPerformanceEnabled();

        long start = 0;
        if (performanceLogging) {
            start = System.nanoTime();
        }

        final ItemStack[] contents = inventory.getStorageContents();

        if (inventory.getType() == InventoryType.PLAYER) {
            sortPlayerInventory((PlayerInventory) inventory, contents);
        } else {
            sortStorageInventory(inventory, contents);
        }

        if (performanceLogging) {
            logger.logElapsedTime(start, "Inventory sort (" + inventory.getType() + ")");
        }
    }

    private void sortStorageInventory(final @NonNull Inventory inventory, final ItemStack[] contents) {
        final ItemStack[] sortedItems = sortItems(contents);

        final ItemStack[] finalContents =
                new ItemStack[contents.length];

        // Copy the sorted items while preserving the remaining slots as null
        System.arraycopy(
                sortedItems,
                0,
                finalContents,
                0,
                sortedItems.length
        );

        inventory.setStorageContents(finalContents);
    }

    private void sortPlayerInventory(final @NonNull PlayerInventory inventory, final ItemStack[] contents) {
        // ignores the hotbar in the players inventory
        final ItemStack[] sortableContents = Arrays.copyOfRange(
                contents,
                HOTBAR_SIZE,
                contents.length);

        final ItemStack[] sortedContents = sortItems(sortableContents);

        // keep the original to keep the hotbar from changing
        final ItemStack[] finalContents = contents.clone();

        // Only empty the inventory above the hotbar
        Arrays.fill(finalContents, HOTBAR_SIZE, finalContents.length, null);

        // put back the items after the hotbar
        System.arraycopy(
                sortedContents,
                0,
                finalContents,
                HOTBAR_SIZE,
                sortedContents.length
        );

        inventory.setStorageContents(finalContents);
    }

    private ItemStack @NonNull [] sortItems(final ItemStack[] items) {
        final ItemStack[] itemsToSort = removeEmptySlots(mergeBlocks(items));

        Arrays.sort(itemsToSort, this::compareItems);

        return itemsToSort;
    }

    private int compareItems(final ItemStack item1, final ItemStack item2) {
        final int w1 = categoryManager.getWeightSafely(item1);
        final int w2 = categoryManager.getWeightSafely(item2);

        if (w1 != w2) {
            return Integer.compare(w2, w1);
        }

        return item1.getType().name().compareTo(item2.getType().name());
    }

    /**
     * Merges matching item stacks together within the array to compress their amounts.
     * <p>
     * This method mutates the provided array in-place. It iterates through the items,
     * and if a stack is not full, it looks ahead to find similar items to pull amounts from,
     * respecting the maximum stack size for that item type.
     *
     * @param items The array of item stacks to merge (can contain nulls)
     * @return The same array with items merged, or null if the input was null
     */
    @Contract("_ -> param1")
    private @Nullable ItemStack @NonNull [] mergeBlocks(final @Nullable ItemStack @NonNull [] items) {

        // Find an item stack that has room for more items
        for (int i = 0; i < items.length; i++) {
            final ItemStack item = items[i];
            // Skip empty slots or stacks that are already completely full
            if (item == null || item.getAmount() >= item.getMaxStackSize()) {
                continue;
            }

            // Look ahead in the array for matching items to merge into the current stack
            for (int j = i + 1; j < items.length; j++) {
                final ItemStack item2 = items[j];
                // Skip empty slots
                if (item2 == null || item2.getAmount() == 0) {
                    continue;
                }

                if (item.isSimilar(item2)) {
                    int maxStack = item.getMaxStackSize();
                    int spaceLeft = maxStack - item.getAmount();

                    int amountToMove = Math.min(spaceLeft, item2.getAmount());

                    // Transfer the items from stack J to stack I
                    item.setAmount(item.getAmount() + amountToMove);
                    item2.setAmount(item2.getAmount() - amountToMove);

                    // If stack J is completely empty now, clear the slot entirely
                    if (item2.getAmount() <= 0) {
                        items[j] = null;
                    }

                    // If the target stack I is now full, we can stop looking for more matches
                    if (item.getAmount() >= maxStack) {
                        break;
                    }
                }
            }
        }
        return items;
    }

    /**
     * Removes all empty or null slots from the array, returning a compressed
     * array that contains only valid items.
     *
     * @param items The array to filter (can contain nulls or empty stacks)
     * @return A new array compressed to the exact size of the valid items contained within
     */
    @NotNull
    private ItemStack @NonNull [] removeEmptySlots(final @Nullable ItemStack @NonNull [] items) {
        final ItemStack[] newItems = new ItemStack[items.length];
        int itemCount = 0;

        for (final @Nullable ItemStack item : items) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            newItems[itemCount++] = item;
        }

        return Arrays.copyOf(newItems, itemCount);
    }

    /**
     * Checks whether the given inventory can be sorted by NeatChests.
     *
     * @param inventory the inventory to check
     * @return {@code true} if the inventory is supported, otherwise {@code false}
     */
    public boolean isSortableInventory(final @NonNull Inventory inventory) {
        return switch (inventory.getType()) {
            case PLAYER,
                 CHEST,
                 ENDER_CHEST,
                 SHULKER_BOX -> true;
            default -> false;
        };
    }
}
