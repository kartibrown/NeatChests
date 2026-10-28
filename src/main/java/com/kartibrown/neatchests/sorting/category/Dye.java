package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.config.SortingMode;
import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

public final class Dye extends Category {
    private static final int DYE = 0;

    public Dye() {
        super(1);
    }

    @Override
    public void initialize(final SortingMode mode) {
        final Material[] dyes = {
                Material.BLACK_DYE,
                Material.BLUE_DYE,
                Material.BROWN_DYE,
                Material.CYAN_DYE,
                Material.GRAY_DYE,
                Material.GREEN_DYE,
                Material.LIGHT_BLUE_DYE,
                Material.LIGHT_GRAY_DYE,
                Material.LIME_DYE,
                Material.MAGENTA_DYE,
                Material.ORANGE_DYE,
                Material.PINK_DYE,
                Material.PURPLE_DYE,
                Material.RED_DYE,
                Material.WHITE_DYE,
                Material.YELLOW_DYE
        };

        for (final Material dye : dyes) {
            addWithAutoWeight(DYE, dye);
        }
    }

    @Contract(pure = true)
    @Override
    public boolean containsOrRegister(final Material material) {
        return subCategories[DYE].containsKey(material);
    }
}
