package com.kartibrown.neatchests.sorting.category;

import com.kartibrown.neatchests.sorting.Category;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

public final class Food extends Category {
    private static final int FOOD = 0;

    public Food() {
        super(1);
    }

    @Override
    public void initialize() {
        setStartWeight(baseWeight);

        final Material[] food = {
                Material.ENCHANTED_GOLDEN_APPLE,
                Material.GOLDEN_APPLE,
                Material.APPLE,
                Material.GOLDEN_CARROT,
                Material.COOKED_BEEF,
                Material.COOKED_PORKCHOP,
                Material.COOKED_CHICKEN,
                Material.COOKED_MUTTON,
                Material.COOKED_RABBIT,
                Material.COOKED_COD,
                Material.COOKED_SALMON,
                Material.BREAD,
                Material.COOKIE,
                Material.CAKE,
                Material.PUMPKIN_PIE,
                Material.MUSHROOM_STEW,
                Material.RABBIT_STEW
        };

        for (final Material material : food) {
            addWithAutoWeight(FOOD, material);
        }

        addMaterialIfExists(FOOD, "BEETROOT_SOUP");
        addMaterialIfExists(FOOD, "SUSPICIOUS_STEW");
        addWithAutoWeight(FOOD, Material.BOWL);

        addWithAutoWeight(FOOD, Material.BAKED_POTATO);
        addWithAutoWeight(FOOD, Material.CARROT);
        addWithAutoWeight(FOOD, Material.BEETROOT);
        addWithAutoWeight(FOOD, Material.MELON_SLICE);
        addMaterialIfExists(FOOD, "SWEET_BERRIES");
        addMaterialIfExists(FOOD, "GLOW_BERRIES");
        addMaterialIfExists(FOOD, "DRIED_KELP");

        addWithAutoWeight(FOOD, Material.MILK_BUCKET);
    }

    @Contract(pure = true)
    @Override
    public boolean containsOrRegister(final Material material) {
        return subCategories[FOOD].containsKey(material);
    }
}
