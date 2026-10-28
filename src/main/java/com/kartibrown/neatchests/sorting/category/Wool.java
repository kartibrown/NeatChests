package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.config.SortingMode;
import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public final class Wool extends Category {
    private static final int WOOL = 0;

    public Wool() {
        super(1);
    }

    @Override
    public void initialize(final @NonNull SortingMode mode) {
        initializeStartWeight(baseWeight);

        switch (mode) {
            case CATEGORY, BLOCK_TYPE -> initializeCategory();
            case FAMILY -> initializeFamily();
        }
    }

    private void initializeCategory() {
        addColoredItems(WOOL, "WOOL");
        addColoredItems(WOOL, "WOOL_STAIRS");
        addColoredItems(WOOL, "WOOL_SLAB");
    }

    private void initializeFamily() {
        for(final String color : COLORS) {
            addWoolFamily(color);
        }
    }

    private void addWoolFamily(final String color) {
        addMaterialIfExists(WOOL, color + "_WOOL");
        addMaterialIfExists(WOOL, color + "_WOOL_STAIRS");
        addMaterialIfExists(WOOL, color + "_WOOL_SLAB");
    }

    @Contract(pure = true)
    @Override
    public boolean containsOrRegister(final Material material) {
        return subCategories[WOOL].containsKey(material);
    }
}
