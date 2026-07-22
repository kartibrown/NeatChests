package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

// FALLBACK CLASS
public final class Misc extends Category {

    private static final int OTHER = 0;

    public Misc() {
        super(1);
    }

    @Contract(pure = true)
    @Override
    public boolean tryAdd(final Material material, final int weight) {
        if(subCategories[OTHER].containsKey(material)) {
            return true;
        }

        if(material == Material.DIRT) {

            addToCategory(OTHER, Material.DIRT, weight);
            addToCategory(OTHER, Material.GRASS_BLOCK, weight);
            addToCategory(OTHER, Material.COARSE_DIRT, weight);
            addToCategory(OTHER, Material.PODZOL, weight);

            addMaterialIfExists(OTHER, "ROOTED_DIRT", weight);
            addMaterialIfExists(OTHER, "DIRT_PATH", weight);
            addMaterialIfExists(OTHER, "MYCELIUM", weight);

            return true;
        }

        return false;
    }
}
