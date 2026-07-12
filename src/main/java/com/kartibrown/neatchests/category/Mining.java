package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class Mining extends Category {
    private static final int COMMON_STONE = 0;
    private static final int NETHER_STONE = 1;
    private static final int END_DEEPSLATE = 2;

    public Mining() {
        super(3);

        // --- SUB-CATEGORY 0: DEFAULT OVERWORLD STONE ---
        final Map<Material, Integer> commonStone = createMap(
                false,
                Material.COBBLESTONE,
                Material.STONE,
                Material.SMOOTH_STONE,
                Material.MOSSY_COBBLESTONE
        );

        // Add 1.8-Stones
        addMaterialIfExists(commonStone, "ANDESITE");
        addMaterialIfExists(commonStone, "POLISHED_ANDESITE");
        addMaterialIfExists(commonStone, "DIORITE");
        addMaterialIfExists(commonStone, "POLISHED_DIORITE");
        addMaterialIfExists(commonStone, "GRANITE");
        addMaterialIfExists(commonStone, "POLISHED_GRANITE");

        // --- SUB-CATEGORY 1: NETHER & OTHER ---
        final Map<Material, Integer> netherStone = createMap(
                false,
                Material.NETHERRACK,
                Material.OBSIDIAN
        );
        addMaterialIfExists(netherStone, "BLACKSTONE");
        addMaterialIfExists(netherStone, "POLISHED_BLACKSTONE");
        addMaterialIfExists(netherStone, "CRYING_OBSIDIAN");
        addMaterialIfExists(netherStone, "BASALT");

        // --- SUB-CATEGORY 2: DEEPSLATE & NEW BLOCKS (1.17+) ---
        final Map<Material, Integer> deepslateStone = new EnumMap<>(Material.class);
        addMaterialIfExists(deepslateStone, "DEEPSLATE");
        addMaterialIfExists(deepslateStone, "COBBLED_DEEPSLATE");
        addMaterialIfExists(deepslateStone, "DEEPSLATE_BRICKS");
        addMaterialIfExists(deepslateStone, "TUFF");
        addMaterialIfExists(deepslateStone, "CALCITE");

        // Add them to each sub category
        items[COMMON_STONE].putAll(commonStone);
        items[NETHER_STONE].putAll(netherStone);
        items[END_DEEPSLATE].putAll(deepslateStone);

    }

    @Contract(pure = true)
    @Override
    public boolean tryAdd(final Material material) {
        return items[0].containsKey(material);
    }
}
