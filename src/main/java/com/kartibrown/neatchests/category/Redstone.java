package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public final class Redstone extends Category {
    public Redstone() {
        super(1);

        final Map<Material, Integer> redstone = createMap(
                Material.REDSTONE,
                Material.REDSTONE_BLOCK,
                Material.REDSTONE_TORCH,
                Material.REPEATER,
                Material.COMPARATOR,
                Material.REDSTONE_LAMP,
                Material.PISTON,
                Material.STICKY_PISTON,
                Material.HOPPER,
                Material.REDSTONE_ORE
        );

        addMaterialIfExists(redstone, "OBSERVER", 100);
        addMaterialIfExists(redstone, "COPPER_BULB");

        items[0].putAll(redstone);
    }

    @Override
    public boolean tryAdd(final Material material) {
        return items[0].containsKey(material);
    }
}
