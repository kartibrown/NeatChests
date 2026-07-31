package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public final class Forestry extends Category {
    private static final int BUILD = 0;
    private static final int MISC = 1;
    private static final int LEAVES = 2;
    private static final int SAPLING = 3;

    public Forestry() {
        super(4);
    }

    @Override
    public void initialize() {

        /*
         * BUILD
         */

        // OAK
        addWoodBuild("OAK");

        // Spruce
        addWoodBuild("SPRUCE");

        // Birch
        addWoodBuild("BIRCH");

        // Jungle
        addWoodBuild("JUNGLE");

        // Acacia
        addWoodBuild("ACACIA");

        // Dark Oak
        addWoodBuild("DARK_OAK");

        // Mangrove 1.19
        addWoodBuild("MANGROVE");

        // Cherry 1.20
        addWoodBuild("CHERRY");

        // Pale 1.21.4
        addWoodBuild("PALE_OAK");

        // Bamboo 1.20
        // It's not a building block, but I think this design is correct
        addMaterialIfExists(BUILD, "BAMBOO");
        addMaterialIfExists(BUILD, "SCAFFOLDING");
        addWoodBuild("BAMBOO");

        // Crimson 1.16
        addWoodBuild("CRIMSON");

        // Warped 1.16
        addWoodBuild("WARPED");

        /*
         * MISC
         */

        // Beds
        // Colored beds started at 1.12
        addColoredItems("BED");

        // Chests & Barrel
        addMaterialIfExists(MISC, "BARREL");
        addWithAutoWeight(MISC, Material.CHEST);
        addWithAutoWeight(MISC, Material.ENDER_CHEST);
        addMaterialIfExists(MISC, "COPPER_CHEST");
        addMaterialIfExists(MISC, "EXPOSED_COPPER_CHEST");
        addMaterialIfExists(MISC, "WEATHERED_COPPER_CHEST");
        addMaterialIfExists(MISC, "OXIDIZED_COPPER_CHEST");
        addMaterialIfExists(MISC, "WAXED_COPPER_CHEST");
        addMaterialIfExists(MISC, "WAXED_EXPOSED_COPPER_CHEST");
        addMaterialIfExists(MISC, "WAXED_WEATHERED_COPPER_CHEST");
        addMaterialIfExists(MISC, "WAXED_OXIDIZED_COPPER_CHEST");
        // Shulker Box 1.11
        addMaterialIfExists(MISC, "SHULKER_BOX");
        addColoredItems("SHULKER_BOX");


        addWithAutoWeight(MISC, Material.CRAFTING_TABLE);
        addWithAutoWeight(MISC, Material.COMPOSTER);
        addWithAutoWeight(MISC, Material.LADDER);
        addWithAutoWeight(MISC, Material.BOOKSHELF);
        addMaterialIfExists(MISC, "CHISELED_BOOKSHELF");
        addMaterialIfExists(MISC, "LECTERN");

        // Oak

        addWoodMisc("OAK");

        // Spruce

        addWoodMisc("SPRUCE");

        // Birch

        addWoodMisc("BIRCH");

        // Jungle

        addWoodMisc("JUNGLE");

        // Acacia

        addWoodMisc("ACACIA");

        // Dark Oak

        addWoodMisc("DARK_OAK");

        // Mangrove

        addWoodMisc("MANGROVE");

        // Cherry

        addWoodMisc("CHERRY");

        // Pale

        addWoodMisc("PALE_OAK");

        // Bamboo

        addWoodMisc("BAMBOO");

        // Crimson

        addWoodMisc("CRIMSON");

        // Warped

        addWoodMisc("WARPED");

        /*
         * Leaves
         */

        addWithAutoWeight(LEAVES, Material.OAK_LEAVES);
        addWithAutoWeight(LEAVES, Material.SPRUCE_LEAVES);
        addWithAutoWeight(LEAVES, Material.BIRCH_LEAVES);
        addWithAutoWeight(LEAVES, Material.JUNGLE_LEAVES);
        addWithAutoWeight(LEAVES, Material.ACACIA_LEAVES);
        addWithAutoWeight(LEAVES, Material.DARK_OAK_LEAVES);
        addMaterialIfExists(LEAVES, "MANGROVE_LEAVES");
        addMaterialIfExists(LEAVES, "CHERRY_LEAVES");
        addMaterialIfExists(LEAVES, "PALE_OAK_LEAVES");
        addMaterialIfExists(LEAVES, "AZALEA_LEAVES");
        addMaterialIfExists(LEAVES, "FLOWERING_AZALEA_LEAVES");

        /*
         * SAPLING
         */

        // Saplings are weird before 1.13, IDK
        addMaterialIfExists(SAPLING, "OAK_SAPLING");
        addMaterialIfExists(SAPLING, "SPRUCE_SAPLING");
        addMaterialIfExists(SAPLING, "BIRCH_SAPLING");
        addMaterialIfExists(SAPLING, "JUNGLE_SAPLING");
        addMaterialIfExists(SAPLING, "ACACIA_SAPLING");
        addMaterialIfExists(SAPLING, "DARK_OAK_SAPLING");
        addMaterialIfExists(SAPLING, "MANGROVE_PROPAGULE");
        addMaterialIfExists(SAPLING, "CHERRY_SAPLING");
        addMaterialIfExists(SAPLING, "PALE_OAK_SAPLING");
        addMaterialIfExists(SAPLING, "AZALEA");
        addMaterialIfExists(SAPLING, "FLOWERING_AZALEA");
        addMaterialIfExists(SAPLING, "CRIMSON_FUNGUS");
        addMaterialIfExists(SAPLING, "WARPED_FUNGUS");
    }

    private void addWoodBuild(final String woodType) {
        addMaterialIfExists(BUILD, woodType + "_LOG");
        // Bamboo
        addMaterialIfExists(BUILD, woodType + "_BLOCK");
        // Crimson & Warped
        addMaterialIfExists(BUILD, woodType + "_STEM");
        addMaterialIfExists(BUILD, woodType + "_HYPHAE");

        addMaterialIfExists(BUILD, woodType + "_WOOD");
        addMaterialIfExists(BUILD, "STRIPPED_" + woodType + "_LOG");
        // Bamboo
        addMaterialIfExists(BUILD, "STRIPPED_" + woodType + "_BLOCK");
        // Crimson & Warped
        addMaterialIfExists(BUILD, "STRIPPED_" + woodType + "_STEM");
        addMaterialIfExists(BUILD, "STRIPPED_" + woodType + "_HYPHAE");

        addMaterialIfExists(BUILD, "STRIPPED_" + woodType + "_WOOD");
        addMaterialIfExists(BUILD, woodType + "_PLANKS");
        // Bamboo
        addMaterialIfExists(BUILD, woodType + "_MOSAIC");
        addMaterialIfExists(BUILD, woodType + "_STAIRS");
        // Bamboo
        addMaterialIfExists(BUILD, woodType + "_MOSAIC_STAIRS");
        addMaterialIfExists(BUILD, woodType + "_SLAB");
        // Bamboo
        addMaterialIfExists(BUILD, woodType + "_MOSAIC_SLAB");
        addMaterialIfExists(BUILD, woodType + "_FENCE");
        addMaterialIfExists(BUILD, woodType + "_FENCE_GATE");
        addMaterialIfExists(BUILD, woodType + "_DOOR");
        addMaterialIfExists(BUILD, woodType + "_TRAPDOOR");
        addMaterialIfExists(BUILD, woodType + "_PRESSURE_PLATE");
        addMaterialIfExists(BUILD, woodType + "_BUTTON");
    }

    private void addWoodMisc(final String woodType) {
        addMaterialIfExists(MISC, woodType + "_BOAT");
        // only for bamboo
        addMaterialIfExists(MISC, woodType + "_RAFT");
        addMaterialIfExists(MISC, woodType + "_CHEST_BOAT");
        // only for bamboo
        addMaterialIfExists(MISC, woodType + "_CHEST_RAFT");
        addMaterialIfExists(MISC, woodType + "_SHELF");
        addMaterialIfExists(MISC, woodType + "_SIGN");
        addMaterialIfExists(MISC, woodType + "_HANGING_SIGN");
    }

    private void addColoredItems(final String item) {
        addMaterialIfExists(MISC, "WHITE_" + item);
        addMaterialIfExists(MISC, "LIGHT_GRAY_" + item);
        addMaterialIfExists(MISC, "GRAY_" + item);
        addMaterialIfExists(MISC, "BLACK_" + item);
        addMaterialIfExists(MISC, "BROWN_" + item);
        addMaterialIfExists(MISC, "RED_" + item);
        addMaterialIfExists(MISC, "ORANGE_" + item);
        addMaterialIfExists(MISC, "YELLOW_" + item);
        addMaterialIfExists(MISC, "LIME_" + item);
        addMaterialIfExists(MISC, "GREEN_" + item);
        addMaterialIfExists(MISC, "CYAN_" + item);
        addMaterialIfExists(MISC, "LIGHT_BLUE_" + item);
        addMaterialIfExists(MISC, "BLUE_" + item);
        addMaterialIfExists(MISC, "PURPLE_" + item);
        addMaterialIfExists(MISC, "MAGENTA_" + item);
        addMaterialIfExists(MISC, "PINK_" + item);
    }

    @Contract(pure = true)
    @Override
    public boolean containsOrRegister(final @NonNull Material material) {
        for (final Map<Material, Integer> item : subCategories) {
            if (item.containsKey(material)) {
                return true;
            }
        }

        return false;
    }
}
