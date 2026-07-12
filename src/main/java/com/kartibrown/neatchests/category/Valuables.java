package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;

import java.util.Map;

public final class Valuables extends Category {
    public Valuables() {
        super(1);

        final Map<Material, Integer> valuables = createMap(
                Material.DIAMOND,
                Material.DIAMOND_BLOCK,
                Material.EMERALD,
                Material.EMERALD_BLOCK,
                Material.GOLD_INGOT,
                Material.GOLD_BLOCK,
                Material.IRON_INGOT,
                Material.IRON_BLOCK,
                Material.LAPIS_LAZULI,
                Material.LAPIS_BLOCK
        );

        addMaterialIfExists(valuables, "NETHERITE_INGOT", MAX_WEIGHT);
        addMaterialIfExists(valuables, "NETHERITE_BLOCK", MAX_WEIGHT - 1);
        addMaterialIfExists(valuables, "COPPER_INGOT");
        addMaterialIfExists(valuables, "COPPER_BLOCK");

        items[0].putAll(valuables);
    }

    @Override
    public boolean tryAdd(final Material material) {
        return items[0].containsKey(material);
    }
}
