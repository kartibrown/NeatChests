package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;

public final class Template extends Category {
    private static final int TEMPLATE = 0;

    public Template()
    {
        super(1);

        setBaseWeight(TEMPLATE, 300);
    }

    @Override
    public boolean tryAdd(final Material material) {
        if (material.name().endsWith("_SMITHING_TEMPLATE")) {
            addWithAutoWeight(TEMPLATE, material);
            return true;
        }

        return false;
    }
}
