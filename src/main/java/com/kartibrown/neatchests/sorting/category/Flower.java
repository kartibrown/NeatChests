package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.config.SortingMode;
import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

public final class Flower extends Category {
    private static final int FLOWER = 0;

    public Flower() {
        super(1);
    }

    @Override
    public void initialize(final SortingMode mode) {
        initializeStartWeight(baseWeight);

        addWithAutoWeight(FLOWER, Material.FLOWER_POT);

        /*
         * FLOWERS I THINK
         */

        addWithAutoWeight(FLOWER, Material.DANDELION);
        addWithAutoWeight(FLOWER, Material.POTTED_DANDELION);
        addMaterialIfExists(FLOWER, "GOLDEN_DANDELION");
        addMaterialIfExists(FLOWER, "POTTED_GOLDEN_DANDELION");

        final Material[] flowers = {
                Material.POPPY,
                Material.POTTED_POPPY,
                Material.BLUE_ORCHID,
                Material.POTTED_BLUE_ORCHID,
                Material.ALLIUM,
                Material.POTTED_ALLIUM,
                Material.AZURE_BLUET,
                Material.POTTED_AZURE_BLUET,
                Material.RED_TULIP,
                Material.POTTED_RED_TULIP,
                Material.ORANGE_TULIP,
                Material.POTTED_ORANGE_TULIP,
                Material.WHITE_TULIP,
                Material.POTTED_WHITE_TULIP,
                Material.PINK_TULIP,
                Material.POTTED_PINK_TULIP,
                Material.OXEYE_DAISY,
                Material.POTTED_OXEYE_DAISY
        };

        for (final Material flower : flowers) {
            addWithAutoWeight(FLOWER, flower);
        }

        addMaterialIfExists(FLOWER, "CORNFLOWER");
        addMaterialIfExists(FLOWER, "POTTED_CORNFLOWER");
        addMaterialIfExists(FLOWER, "LILY_OF_THE_VALLEY");
        addMaterialIfExists(FLOWER, "POTTED_LILY_OF_THE_VALLEY");
        addMaterialIfExists(FLOWER, "TORCHFLOWER");
        addMaterialIfExists(FLOWER, "POTTED_TORCHFLOWER");
        addMaterialIfExists(FLOWER, "TORCHFLOWER_CROP");
        addMaterialIfExists(FLOWER, "TORCHFLOWER_SEEDS");
        addMaterialIfExists(FLOWER, "CACTUS_FLOWER");

        addWithAutoWeight(FLOWER, Material.POTTED_CACTUS);

        addMaterialIfExists(FLOWER, "CLOSED_EYEBLOSSOM");
        addMaterialIfExists(FLOWER, "POTTED_CLOSED_EYEBLOSSOM");
        addMaterialIfExists(FLOWER, "OPEN_EYEBLOSSOM");
        addMaterialIfExists(FLOWER, "POTTED_OPEN_EYEBLOSSOM");
        addMaterialIfExists(FLOWER, "WITHER_ROSE");
        addMaterialIfExists(FLOWER, "POTTED_WITHER_ROSE");
        addMaterialIfExists(FLOWER, "PINK_PETALS");
        addMaterialIfExists(FLOWER, "WILDFLOWERS");
        addMaterialIfExists(FLOWER, "SPORE_BLOSSOM");
        addMaterialIfExists(FLOWER, "FIREFLY_BUSH");

        addWithAutoWeight(FLOWER, Material.SUNFLOWER);
        addWithAutoWeight(FLOWER, Material.LILAC);
        addWithAutoWeight(FLOWER, Material.ROSE_BUSH);
        addWithAutoWeight(FLOWER, Material.PEONY);

        addMaterialIfExists(FLOWER, "PITCHER_PLANT");
        addMaterialIfExists(FLOWER, "PITCHER_CROP");
        addMaterialIfExists(FLOWER, "PITCHER_POD");

        /*
         * SAPLINGS
         */

        addWithAutoWeight(FLOWER, Material.DEAD_BUSH);
        addWithAutoWeight(FLOWER, Material.POTTED_DEAD_BUSH);

        addWithAutoWeight(FLOWER, Material.POTTED_OAK_SAPLING);
        // Azalea here cuz it's made of oak
        addMaterialIfExists(FLOWER, "POTTED_AZALEA_BUSH");
        addMaterialIfExists(FLOWER, "POTTED_FLOWERING_AZALEA_BUSH");

        addWithAutoWeight(FLOWER, Material.POTTED_SPRUCE_SAPLING);
        addWithAutoWeight(FLOWER, Material.POTTED_BIRCH_SAPLING);
        addWithAutoWeight(FLOWER, Material.POTTED_JUNGLE_SAPLING);
        addWithAutoWeight(FLOWER, Material.POTTED_ACACIA_SAPLING);
        addWithAutoWeight(FLOWER, Material.POTTED_DARK_OAK_SAPLING);
        addMaterialIfExists(FLOWER, "POTTED_MANGROVE_PROPAGULE");
        addMaterialIfExists(FLOWER, "POTTED_CHERRY_SAPLING");
        addMaterialIfExists(FLOWER, "POTTED_PALE_OAK_SAPLING");

        /*
         * OTHER PLANTS AND POTTED ITEMS
         */

        addMaterialIfExists(FLOWER, "POTTED_BAMBOO");
        addWithAutoWeight(FLOWER, Material.POTTED_BROWN_MUSHROOM);
        addMaterialIfExists(FLOWER, "CRIMSON_FUNGUS");
        addMaterialIfExists(FLOWER, "POTTED_CRIMSON_FUNGUS");
        addMaterialIfExists(FLOWER, "CRIMSON_ROOTS");
        addMaterialIfExists(FLOWER, "POTTED_CRIMSON_ROOTS");
        addMaterialIfExists(FLOWER, "WARPED_FUNGUS");
        addMaterialIfExists(FLOWER, "POTTED_WARPED_FUNGUS");
        addMaterialIfExists(FLOWER, "WARPED_ROOTS");
        addMaterialIfExists(FLOWER, "POTTED_WARPED_ROOTS");
        addWithAutoWeight(FLOWER, Material.FERN);
        addWithAutoWeight(FLOWER, Material.POTTED_FERN);
    }

    @Contract(pure = true)
    @Override
    public boolean containsOrRegister(final Material material) {
        return subCategories[FLOWER].containsKey(material);
    }
}
