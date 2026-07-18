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

    public ItemStack @NonNull [] sortChestItems(@Nullable final ItemStack[] items) {
        final ItemStack[] itemsToSort = removeEmptySlots(mergeBlocks(items));
        final ItemStack[] sortedItems = new ItemStack[itemsToSort.length];

        int sortedIndex = 0;
        // using long because the max size of a chest is 54 slots
        // and an int is 32 bit which is not enough for the 54 slots
        long itemDoneCheck = 0L;

                for (int currentWeight = Category.MAX_WEIGHT; currentWeight > 0; currentWeight--) {
                    for (int itemIndex = 0; itemIndex < itemsToSort.length; ++itemIndex) {
                        // Check if an item is done
                        if ((itemDoneCheck & (1L << itemIndex)) != 0) {
                            continue;
                        }

                        final ItemStack item = itemsToSort[itemIndex];

                        final Category matchedCategory = findCategoryFor(item.getType());

                        if (matchedCategory != null) {
                            final Integer itemWeight = matchedCategory.getWeightFor(item.getType());

                            if (itemWeight != null && itemWeight == currentWeight) {
                                sortedItems[sortedIndex] = item;
                                sortedIndex++;
                                // Set the chest slot as done (true bitwise)
                                itemDoneCheck |= (1L << itemIndex);
                            }
                        }
                    }
                }

        return sortedItems;
    }

    private @Nullable Category findCategoryFor(final Material material) {
        for (final Category category : categories) {
            if (category.tryAdd(material)) {
                return category;
            }
        }
        return null;
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
