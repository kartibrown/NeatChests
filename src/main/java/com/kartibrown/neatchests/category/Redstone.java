package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;

public final class Redstone extends Category {
    private static final int REDSTONE = 0;

    public Redstone() {
        super(1);

        setBaseWeight(REDSTONE, 1000);

        final Material[] redstone = {
                Material.REDSTONE,
                Material.REDSTONE_BLOCK,
                Material.REDSTONE_TORCH,
                Material.REPEATER,
                Material.COMPARATOR,
                Material.DAYLIGHT_DETECTOR,
                Material.LEVER,
                Material.STONE_BUTTON,
                Material.STONE_PRESSURE_PLATE,
                Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
                Material.HEAVY_WEIGHTED_PRESSURE_PLATE,
                Material.TRIPWIRE_HOOK,
                Material.REDSTONE_LAMP,
                Material.PISTON,
                Material.STICKY_PISTON,
                Material.DISPENSER,
                Material.DROPPER,
                Material.HOPPER
        };

        for (final Material mat : redstone){
            addWithAutoWeight(REDSTONE, mat);
        }

        addMaterialIfExists(REDSTONE, "CRAFTER");
        addMaterialIfExists(REDSTONE, "OBSERVER");
        addMaterialIfExists(REDSTONE, "TARGET");
        addMaterialIfExists(REDSTONE, "CALIBRATED_SCULK_SENSOR");
        addMaterialIfExists(REDSTONE, "SCULK_SENSOR");
        addMaterialIfExists(REDSTONE, "SCULK_SHRIEKER");
        addMaterialIfExists(REDSTONE, "CALIBRATED_SCULK_SENSOR");
        addMaterialIfExists(REDSTONE, "COPPER_BULB");
    }

    @Override
    public boolean tryAdd(final Material material) {
        return subCategories[REDSTONE].containsKey(material);
    }
}
