package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public final class Template extends Category {
    private static final int TEMPLATE = 0;

    public Template()
    {
        super(1);

        setBaseWeight(TEMPLATE, MIN_WEIGHT + 200);
    }

    @Contract("null, _ -> false")
    @Override
    public boolean tryAdd(final Material material, final int weight) {
        if (material != null && material.name().endsWith("_SMITHING_TEMPLATE")) {
            addWithAutoWeight(TEMPLATE, material);
            return true;
        }

        return false;
    }
}
