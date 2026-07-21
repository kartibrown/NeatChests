package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
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

            add(Material.DIRT, weight);
            add(Material.GRASS_BLOCK, weight);
            add(Material.COARSE_DIRT, weight);
            add(Material.PODZOL, weight);

            addMaterialIfExists(OTHER, "ROOTED_DIRT", weight);
            addMaterialIfExists(OTHER, "DIRT_PATH", weight);
            addMaterialIfExists(OTHER, "MYCELIUM", weight);
        }

        return false;
    }

    @Override
    public void add(final Material material, final int weight) {
        // The SortingManager couldn't find a home for this block,
        // so it drops here and gets a sorting weight.
        addToCategory(OTHER, material, weight);
    }
}
