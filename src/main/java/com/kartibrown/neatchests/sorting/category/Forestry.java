package com.kartibrown.neatchests.sorting.category;

import com.destroystokyo.paper.MaterialSetTag;
import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public final class Forestry extends Category {
    private static final int BUILD = 0;
    private static final int MISC = 1;
    private static final int SAPLING = 2;

    private static final int BUILD_OFFSET = 0;
    private static final int MISC_OFFSET = 200;
    private static final int SAPLING_OFFSET = 400;

    public Forestry() {
        super(3);
    }

    @Override
    public void initialize() {

        /*
         * BUILD
         */

         // OAK

        addWithAutoWeight(BUILD, Material.OAK_LOG);
        addMaterialIfExists(BUILD, "OAK_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_OAK_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_OAK_WOOD");
        addWithAutoWeight(BUILD, Material.OAK_PLANKS);
        addWithAutoWeight(BUILD, Material.OAK_STAIRS);
        addWithAutoWeight(BUILD, Material.OAK_SLAB);
        addWithAutoWeight(BUILD, Material.OAK_FENCE);
        addWithAutoWeight(BUILD, Material.OAK_FENCE_GATE);
        addWithAutoWeight(BUILD, Material.OAK_DOOR);
        addWithAutoWeight(BUILD, Material.OAK_TRAPDOOR);
        addWithAutoWeight(BUILD, Material.OAK_PRESSURE_PLATE);
        // hmm oak button was added in 1.13, before that it was called WOODEN_BUTTON
        // it's the same for every button here in this scope
        addMaterialIfExists(BUILD, "OAK_BUTTON");

        // Spruce

        addWithAutoWeight(BUILD, Material.SPRUCE_LOG);
        addMaterialIfExists(BUILD, "SPRUCE_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_SPRUCE_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_SPRUCE_WOOD");
        addWithAutoWeight(BUILD, Material.SPRUCE_PLANKS);
        addWithAutoWeight(BUILD, Material.SPRUCE_STAIRS);
        addWithAutoWeight(BUILD, Material.SPRUCE_SLAB);
        addWithAutoWeight(BUILD, Material.SPRUCE_FENCE);
        addWithAutoWeight(BUILD, Material.SPRUCE_FENCE_GATE);
        addWithAutoWeight(BUILD, Material.SPRUCE_DOOR);
        addWithAutoWeight(BUILD, Material.SPRUCE_TRAPDOOR);
        addWithAutoWeight(BUILD, Material.SPRUCE_PRESSURE_PLATE);

        addMaterialIfExists(BUILD, "SPRUCE_BUTTON");

        // Birch

        addWithAutoWeight(BUILD, Material.BIRCH_LOG);
        addMaterialIfExists(BUILD, "BIRCH_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_BIRCH_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_BIRCH_WOOD");
        addWithAutoWeight(BUILD, Material.BIRCH_PLANKS);
        addWithAutoWeight(BUILD, Material.BIRCH_STAIRS);
        addWithAutoWeight(BUILD, Material.BIRCH_SLAB);
        addWithAutoWeight(BUILD, Material.BIRCH_FENCE);
        addWithAutoWeight(BUILD, Material.BIRCH_FENCE_GATE);
        addWithAutoWeight(BUILD, Material.BIRCH_DOOR);
        addWithAutoWeight(BUILD, Material.BIRCH_TRAPDOOR);
        addWithAutoWeight(BUILD, Material.BIRCH_PRESSURE_PLATE);

        addMaterialIfExists(BUILD, "BIRCH_BUTTON");

        // Jungle

        addWithAutoWeight(BUILD, Material.JUNGLE_LOG);
        addMaterialIfExists(BUILD, "JUNGLE_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_JUNGLE_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_JUNGLE_WOOD");
        addWithAutoWeight(BUILD, Material.JUNGLE_PLANKS);
        addWithAutoWeight(BUILD, Material.JUNGLE_STAIRS);
        addWithAutoWeight(BUILD, Material.JUNGLE_SLAB);
        addWithAutoWeight(BUILD, Material.JUNGLE_FENCE);
        addWithAutoWeight(BUILD, Material.JUNGLE_FENCE_GATE);
        addWithAutoWeight(BUILD, Material.JUNGLE_DOOR);
        addWithAutoWeight(BUILD, Material.JUNGLE_TRAPDOOR);
        addWithAutoWeight(BUILD, Material.JUNGLE_PRESSURE_PLATE);

        addMaterialIfExists(BUILD, "JUNGLE_BUTTON");

        // Acacia

        addWithAutoWeight(BUILD, Material.ACACIA_LOG);
        addMaterialIfExists(BUILD, "ACACIA_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_ACACIA_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_ACACIA_WOOD");
        addWithAutoWeight(BUILD, Material.ACACIA_PLANKS);
        addWithAutoWeight(BUILD, Material.ACACIA_STAIRS);
        addWithAutoWeight(BUILD, Material.ACACIA_SLAB);
        addWithAutoWeight(BUILD, Material.ACACIA_FENCE);
        addWithAutoWeight(BUILD, Material.ACACIA_FENCE_GATE);
        addWithAutoWeight(BUILD, Material.ACACIA_DOOR);
        addWithAutoWeight(BUILD, Material.ACACIA_TRAPDOOR);
        addWithAutoWeight(BUILD, Material.ACACIA_PRESSURE_PLATE);

        addMaterialIfExists(BUILD, "ACACIA_BUTTON");

        // Dark Oak

        addWithAutoWeight(BUILD, Material.DARK_OAK_LOG);
        addMaterialIfExists(BUILD, "DARK_OAK_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_DARK_OAK_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_DARK_OAK_WOOD");
        addWithAutoWeight(BUILD, Material.DARK_OAK_PLANKS);
        addWithAutoWeight(BUILD, Material.DARK_OAK_STAIRS);
        addWithAutoWeight(BUILD, Material.DARK_OAK_SLAB);
        addWithAutoWeight(BUILD, Material.DARK_OAK_FENCE);
        addWithAutoWeight(BUILD, Material.DARK_OAK_FENCE_GATE);
        addWithAutoWeight(BUILD, Material.DARK_OAK_DOOR);
        addWithAutoWeight(BUILD, Material.DARK_OAK_TRAPDOOR);
        addWithAutoWeight(BUILD, Material.DARK_OAK_PRESSURE_PLATE);

        addMaterialIfExists(BUILD, "DARK_OAK_BUTTON");

        // Mangrove 1.19

        addMaterialIfExists(BUILD, "MANGROVE_LOG");
        addMaterialIfExists(BUILD, "MANGROVE_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_MANGROVE_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_MANGROVE_WOOD");
        addMaterialIfExists(BUILD, "MANGROVE_PLANKS");
        addMaterialIfExists(BUILD, "MANGROVE_STAIRS");
        addMaterialIfExists(BUILD, "MANGROVE_SLAB");
        addMaterialIfExists(BUILD, "MANGROVE_FENCE");
        addMaterialIfExists(BUILD, "MANGROVE_FENCE_GATE");
        addMaterialIfExists(BUILD, "MANGROVE_DOOR");
        addMaterialIfExists(BUILD, "MANGROVE_TRAPDOOR");
        addMaterialIfExists(BUILD, "MANGROVE_PRESSURE_PLATE");

        addMaterialIfExists(BUILD, "MANGROVE_BUTTON");

        // Cherry 1.20

        addMaterialIfExists(BUILD, "CHERRY_LOG");
        addMaterialIfExists(BUILD, "CHERRY_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_CHERRY_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_CHERRY_WOOD");
        addMaterialIfExists(BUILD, "CHERRY_PLANKS");
        addMaterialIfExists(BUILD, "CHERRY_STAIRS");
        addMaterialIfExists(BUILD, "CHERRY_SLAB");
        addMaterialIfExists(BUILD, "CHERRY_FENCE");
        addMaterialIfExists(BUILD, "CHERRY_FENCE_GATE");
        addMaterialIfExists(BUILD, "CHERRY_DOOR");
        addMaterialIfExists(BUILD, "CHERRY_TRAPDOOR");
        addMaterialIfExists(BUILD, "CHERRY_PRESSURE_PLATE");

        addMaterialIfExists(BUILD, "CHERRY_BUTTON");

        // Pale 1.21.4

        addMaterialIfExists(BUILD, "PALE_OAK_LOG");
        addMaterialIfExists(BUILD, "PALE_OAK_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_PALE_OAK_LOG");
        addMaterialIfExists(BUILD, "STRIPPED_PALE_OAK_WOOD");
        addMaterialIfExists(BUILD, "PALE_OAK_PLANKS");
        addMaterialIfExists(BUILD, "PALE_OAK_STAIRS");
        addMaterialIfExists(BUILD, "PALE_OAK_SLAB");
        addMaterialIfExists(BUILD, "PALE_OAK_FENCE");
        addMaterialIfExists(BUILD, "PALE_OAK_FENCE_GATE");
        addMaterialIfExists(BUILD, "PALE_OAK_DOOR");
        addMaterialIfExists(BUILD, "PALE_OAK_TRAPDOOR");
        addMaterialIfExists(BUILD, "PALE_OAK_PRESSURE_PLATE");

        addMaterialIfExists(BUILD, "PALE_OAK_BUTTON");

        // Bamboo 1.20

        addMaterialIfExists(BUILD, "BAMBOO_BLOCK");
        addMaterialIfExists(BUILD, "STRIPPED_BAMBOO_BLOCK");
        addMaterialIfExists(BUILD, "BAMBOO_PLANKS");
        addMaterialIfExists(BUILD, "BAMBOO_MOSAIC");
        addMaterialIfExists(BUILD, "BAMBOO_STAIRS");
        addMaterialIfExists(BUILD, "BAMBOO_MOSAIC_STAIRS");
        addMaterialIfExists(BUILD, "BAMBOO_SLAB");
        addMaterialIfExists(BUILD, "BAMBOO_MOSAIC_SLAB");
        addMaterialIfExists(BUILD, "BAMBOO_FENCE");
        addMaterialIfExists(BUILD, "BAMBOO_FENCE_GATE");
        addMaterialIfExists(BUILD, "BAMBOO_DOOR");
        addMaterialIfExists(BUILD, "BAMBOO_TRAPDOOR");
        addMaterialIfExists(BUILD, "BAMBOO_PRESSURE_PLATE");

        addMaterialIfExists(BUILD, "BAMBOO_BUTTON");

        // Crimson 1.16

        addMaterialIfExists(BUILD, "CRIMSON_STEM");
        addMaterialIfExists(BUILD, "CRIMSON_HYPHAE");
        addMaterialIfExists(BUILD, "STRIPPED_CRIMSON_STEM");
        addMaterialIfExists(BUILD, "STRIPPED_CRIMSON_HYPHAE");
        addMaterialIfExists(BUILD, "CRIMSON_PLANKS");
        addMaterialIfExists(BUILD, "CRIMSON_STAIRS");
        addMaterialIfExists(BUILD, "CRIMSON_SLAB");
        addMaterialIfExists(BUILD, "CRIMSON_FENCE");
        addMaterialIfExists(BUILD, "CRIMSON_FENCE_GATE");
        addMaterialIfExists(BUILD, "CRIMSON_DOOR");
        addMaterialIfExists(BUILD, "CRIMSON_TRAPDOOR");
        addMaterialIfExists(BUILD, "CRIMSON_PRESSURE_PLATE");

        addMaterialIfExists(BUILD, "CRIMSON_BUTTON");

        // Warped 1.16

        addMaterialIfExists(BUILD, "WARPED_STEM");
        addMaterialIfExists(BUILD, "WARPED_HYPHAE");
        addMaterialIfExists(BUILD, "STRIPPED_WARPED_STEM");
        addMaterialIfExists(BUILD, "STRIPPED_WARPED_HYPHAE");
        addMaterialIfExists(BUILD, "WARPED_PLANKS");
        addMaterialIfExists(BUILD, "WARPED_STAIRS");
        addMaterialIfExists(BUILD, "WARPED_SLAB");
        addMaterialIfExists(BUILD, "WARPED_FENCE");
        addMaterialIfExists(BUILD, "WARPED_FENCE_GATE");
        addMaterialIfExists(BUILD, "WARPED_DOOR");
        addMaterialIfExists(BUILD, "WARPED_TRAPDOOR");
        addMaterialIfExists(BUILD, "WARPED_PRESSURE_PLATE");

        addMaterialIfExists(BUILD, "WARPED_BUTTON");

        /*
         * MISC
         */

        /*
         * SAPLING
         */
    }

    @Contract(pure = true)
    @Override
    public boolean tryAdd(final @NonNull Material material) {
        for (final Map<Material, Integer> item : subCategories) {
            if (item.containsKey(material)) {
                return true;
            }
        }

        return false;
    }
}
