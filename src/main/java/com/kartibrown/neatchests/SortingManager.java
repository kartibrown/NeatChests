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

        final int totalMaterials = materials.length;

        for (int materialIndex = 0; materialIndex < totalMaterials; materialIndex++) {
            final Material material = materials[materialIndex];

            if (material.isAir() || material.isLegacy()) {
                continue;
            }

            // Highest weight to index 0 (A)
            // Lowest weight to last index (Z)
            int weight = totalMaterials - materialIndex;

            for (int i = 0; i < categories.length; ++i) {
                if (categories[i].tryAdd(material, weight)) {
                    break;
                }

                // Catch all other blocks
                // if no categories wanted the block
                if (i >= categories.length - 1) {
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

        Arrays.sort(itemsToSort, (item1, item2) -> {
            int w1 = getWeightSafely(item1);
            int w2 = getWeightSafely(item2);

            // highest weight first
            return Integer.compare(w2, w1);
        });

        return itemsToSort;
    }

    /**
     * Gets the weight safely from the item, will return -1 if null
     *
     * @param item The item to get the weight from
     * @return Returns -1 if item is null or if Category is null or if the Integer
     * to get the weight is null
     */
    private int getWeightSafely(final @Nullable ItemStack item) {
        if (item == null) {
            return -1;
        }

        final Category category = findCategoryFor(item.getType());
        if (category == null) {
            return -1;
        }

        final Integer weight = category.getWeightFor(item.getType());
        return weight != null ? weight : -1;
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
            if (category.tryAdd(material, -1)) {
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
