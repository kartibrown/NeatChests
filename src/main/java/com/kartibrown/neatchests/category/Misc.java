package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

// FALLBACK CLASS
public final class Misc extends Category {

    private static final int D_LETTER_WEIGHT = 110;

    // Sets a letter based weight in SortingManager, and A is 125 weight
    // so we give it some room, 130 to not get other items entangled with misc's
    public static final int MISC_MAX_WEIGHT = 130;

    private static final int OTHER = 0;

    public Misc() {
        super(1);

        final Material[] dirt = {
                    Material.DIRT,
                    Material.GRASS_BLOCK,
                    Material.COARSE_DIRT,
                    Material.PODZOL
        };

        for(final Material mat : dirt) {
            addToCategory(OTHER, mat, D_LETTER_WEIGHT);
        }

        addMaterialIfExists(OTHER, "ROOTED_DIRT", D_LETTER_WEIGHT);
        addMaterialIfExists(OTHER, "DIRT_PATH", D_LETTER_WEIGHT);
        addMaterialIfExists(OTHER, "MYCELIUM", D_LETTER_WEIGHT);
    }

    @Contract(pure = true)
    @Override
    public boolean tryAdd(final Material material) {
        return subCategories[OTHER].containsKey(material);
    }

    @Override
    public void add(final Material material, final int weight) {
        // The SortingManager couldn't find a home for this block,
        // so it drops here and gets a sorting weight.
        addToCategory(OTHER, material, weight);
    }
}
