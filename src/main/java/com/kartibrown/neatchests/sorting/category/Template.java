package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

public final class Template extends Category {
    private static final int TEMPLATE = 0;

    public Template()
    {
        super(1);
    }

    @Contract("null -> false")
    @Override
    public boolean tryAdd(final Material material) {
        if (material != null && material.name().endsWith("_SMITHING_TEMPLATE")) {
            addWithAutoWeight(TEMPLATE, material);
            return true;
        }

        return false;
    }
}
