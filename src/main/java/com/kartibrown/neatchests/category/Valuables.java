package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

public final class Valuables extends Category {
    private static final int VALUABLES = 0;

    public Valuables() {
        super(1);

        setBaseWeight(VALUABLES, MAX_WEIGHT);

        addMaterialIfExists(VALUABLES, "NETHERITE_INGOT");
        addMaterialIfExists(VALUABLES, "NETHERITE_BLOCK");
        addMaterialIfExists(VALUABLES, "NETHERITE_SCRAP");

        final Material[] mainValuables = {
                Material.DIAMOND,
                Material.DIAMOND_BLOCK,
                Material.EMERALD,
                Material.EMERALD_BLOCK,
                Material.GOLD_NUGGET,
                Material.GOLD_INGOT,
                Material.GOLD_BLOCK,
                Material.IRON_NUGGET,
                Material.IRON_INGOT,
                Material.IRON_BLOCK,
                Material.LAPIS_LAZULI,
                Material.LAPIS_BLOCK
        };

        for (final Material mat : mainValuables) {
            addWithAutoWeight(VALUABLES, mat);
        }

        addMaterialIfExists(VALUABLES, "COPPER_NUGGET");
        addMaterialIfExists(VALUABLES, "COPPER_INGOT");
        addMaterialIfExists(VALUABLES, "COPPER_BLOCK");
    }

    @Contract(pure = true)
    @Override
    public boolean tryAdd(final Material material) {
        return subCategories[VALUABLES].containsKey(material);
    }
}
