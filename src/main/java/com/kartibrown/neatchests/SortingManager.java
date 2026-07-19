package com.kartibrown.neatchests;

import com.kartibrown.neatchests.category.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.Comparator;

public final class SortingManager {

    private final Category[] categories;

    public SortingManager() {
        categories = new Category[]{
                new Valuables(),
                new Equipment(),
                new Redstone(),
                new Forestry(),
                new Template(),
                new Misc()
        };

        final Material[] materials = Material.values();
        // sorts the array alphabetically
        Arrays.sort(materials, Comparator.comparing(Material::name));

        for (final Material material : materials) {
            if (material.isAir() || material.isLegacy()) {
                continue;
            }

            for (int i = 0; i < categories.length; ++i) {
                if (categories[i].tryAdd(material)) {
                    break;
                }

                // Catch all other blocks
                // if no categories wanted the block
                if (i >= categories.length - 1) {
                    char firstLetter = material.name().charAt(0);

                    // 'Z' (90) minus 'A' (65) is 25.
                    // A: (90 - 65) * 5 = 125
                    // B: (90 - 66) * 5 = 120
                    // Z: (90 - 90) * 5 = 0
                    int weight = ('Z' - firstLetter) * 5;

                    categories[categories.length - 1].add(material, weight);
                }
            }
        }
    }

    /**
     * Sorts chest items based on their category weight.
     * <p>
     * The sorting process follows three steps:<br>
     * 1. Merges stacks of the same item type (mergeBlocks).<br>
     * 2. Removes any remaining empty slots (removeEmptySlots).<br>
     * 3. Sorts the cleaned items in descending order based on their assigned weight.
     *
     * @param items The chest items to sort (can contain null/empty slots)
     * @return A new array containing the sorted items, compressed without empty spaces
     */
    public ItemStack @NonNull [] sortChestItems(@Nullable final ItemStack[] items) {
        final ItemStack[] itemsToSort = removeEmptySlots(mergeBlocks(items));
        final ItemStack[] sortedItems = new ItemStack[itemsToSort.length];

        int sortedIndex = 0;
        // We use a 64-bit 'long' as a bitmask to keep track of processed items.
        // Since a double chest in Minecraft has up to 54 slots, a standard 32-bit 'int'
        // does not have enough bits to represent each slot index individually.
        long itemDoneCheck = 0L;

        // Priority/Bucket sort: Iterate from the highest weight down to 1.
        // This ensures heavier (higher priority) items are placed first in the sorted array.
        for (int currentWeight = Category.MAX_WEIGHT; currentWeight > 0; currentWeight--) {
            for (int itemIndex = 0; itemIndex < itemsToSort.length; ++itemIndex) {
                // Bitwise check: If the bit at 'itemIndex' is 1, this item has already been sorted
                if ((itemDoneCheck & (1L << itemIndex)) != 0) {
                    continue;
                }

                final ItemStack item = itemsToSort[itemIndex];

                final Category matchedCategory = findCategoryFor(item.getType());

                if (matchedCategory != null) {
                    final Integer itemWeight = matchedCategory.getWeightFor(item.getType());

                    // If the item matches the current weight tier, place it in the next available slot
                    if (itemWeight != null && itemWeight == currentWeight) {
                        sortedItems[sortedIndex] = item;
                        sortedIndex++;
                        // Mark this item index as processed by setting its bit to 1
                        itemDoneCheck |= (1L << itemIndex);
                    }
                }
            }
        }

        return sortedItems;
    }

    /**
     * Returns the category that the material passed is located in, also adds
     * the material to the category if it needs to add it
     *
     * @param material The material to find the category for
     * @return Returns the category that the material is in
     */
    private @Nullable Category findCategoryFor(final Material material) {
        for (final Category category : categories) {
            if (category.tryAdd(material)) {
                return category;
            }
        }
        return null;
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
    @Contract("null -> null")
    private ItemStack @Nullable [] mergeBlocks(@Nullable final ItemStack[] items) {

        if (items == null) return null;

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
    private ItemStack @NonNull [] removeEmptySlots(@Nullable final ItemStack[] items) {
        final ItemStack[] newItems = new ItemStack[items.length];
        int itemCount = 0;

        for (final ItemStack item : items) {
            if (item == null) {
                continue;
            }

            if (!item.isEmpty()) {
                newItems[itemCount] = item;
                itemCount++;
            }
        }

        return Arrays.copyOf(newItems, itemCount);
    }
}
