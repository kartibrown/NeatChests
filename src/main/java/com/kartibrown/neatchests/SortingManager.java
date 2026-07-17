package com.kartibrown.neatchests;

import com.kartibrown.neatchests.category.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.Map;

public final class SortingManager {
    private final Category[] categories;

    public SortingManager() {
        categories = new Category[]{
                new Valuables(),
                new Equipment(),
                new Redstone(),
                new Forestry(),
                new Misc()
        };

        for (final Material material : Material.values()) {
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
                    categories[categories.length - 1].add(material);
                }
            }
        }
    }

    public ItemStack @NonNull [] sortChestItems(@Nullable final ItemStack[] items) {
        final ItemStack[] itemsToSort = removeEmptySlots(mergeBlocks(items));

        final ItemStack[] sortedItems = new ItemStack[itemsToSort.length];
        int i = 0;

        for (final ItemStack item : itemsToSort) {
            if (belongsToAnyCategory(item.getType())) {
                sortedItems[i] = item;
                i++;
            }
        }

        /*
         * A loop for blocks that couldn't be found
         */

        return sortedItems;
    }

    private boolean belongsToAnyCategory(final Material mat) {
        for (final Category category : categories) {
            for (final Map<Material, Integer> catItem : category.getItems()) {
                if (catItem.containsKey(mat)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Contract("null -> null")
    private ItemStack @Nullable [] mergeBlocks(@Nullable final ItemStack[] items) {
        if (items == null) return null;

        for (int i = 0; i < items.length; i++) {
            final ItemStack item = items[i];
            if (item == null || item.getAmount() >= item.getMaxStackSize()) {
                continue;
            }

            for (int j = i + 1; j < items.length; j++) {
                final ItemStack item2 = items[j];
                if (item2 == null || item2.getAmount() == 0) {
                    continue;
                }

                if (item.isSimilar(item2)) {
                    int maxStack = item.getMaxStackSize();
                    int spaceLeft = maxStack - item.getAmount();

                    int amountToMove = Math.min(spaceLeft, item2.getAmount());

                    item.setAmount(item.getAmount() + amountToMove);
                    item2.setAmount(item2.getAmount() - amountToMove);

                    if (item2.getAmount() <= 0) {
                        items[j] = null;
                    }

                    if (item.getAmount() >= maxStack) {
                        break;
                    }
                }
            }
        }
        return items;
    }

    @NotNull
    private ItemStack @NonNull [] removeEmptySlots(@Nullable final ItemStack[] items) {
        final ItemStack[] newItems = new ItemStack[items.length];
        int itemCount = 0;

        for (final ItemStack item : items) {
            if (item == null) {
                continue;
            }

            if (!item.isEmpty()) {
                newItems[itemCount] = item.clone();
                itemCount++;
            }
        }

        return Arrays.copyOf(newItems, itemCount);
    }
}
