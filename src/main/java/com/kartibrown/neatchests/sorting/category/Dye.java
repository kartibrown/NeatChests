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
        initializeStartWeight(baseWeight);

        for (final String color : COLORS) {
            addMaterialIfExists(DYE, color + "_DYE");
        }
    }

    @Contract(pure = true)
    @Override
    public boolean containsOrRegister(final Material material) {
        return subCategories[DYE].containsKey(material);
    }
}
