package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Map;

public final class Equipment extends Category {
    private static final int NETHERITE = 0;
    private static final int DIAMOND = 1;
    private static final int GOLD = 2;
    private static final int IRON = 3;
    private static final int CHAINMAIL = 4;
    private static final int COPPER = 5;
    private static final int STONE = 6;
    private static final int WOOD = 7;
    private static final int LEATHER = 8;
    private static final int MISC = 9;

    public Equipment() {
        super(10);

        // NETHERITE

        setBaseWeight(NETHERITE, MAX_WEIGHT - 200);

        final Material[] netherite = {
                Material.NETHERITE_AXE,
                Material.NETHERITE_HOE,
                Material.NETHERITE_PICKAXE,
                Material.NETHERITE_SHOVEL,
                Material.NETHERITE_SWORD,
                Material.NETHERITE_HELMET,
                Material.NETHERITE_CHESTPLATE,
                Material.NETHERITE_LEGGINGS,
                Material.NETHERITE_BOOTS
        };

        for(final Material mat : netherite) {
            addWithAutoWeight(NETHERITE, mat);
        }

        addMaterialIfExists(NETHERITE, "NETHERITE_HORSE_ARMOR");
        addMaterialIfExists(NETHERITE, "NETHERITE_NAUTILUS_ARMOR");
        addMaterialIfExists(NETHERITE, "NETHERITE_SPEAR");

        // DIAMOND

        setBaseWeight(DIAMOND, MAX_WEIGHT - 250);

        final Material[] diamond = {
                Material.DIAMOND_AXE,
                Material.DIAMOND_HOE,
                Material.DIAMOND_PICKAXE,
                Material.DIAMOND_SHOVEL,
                Material.DIAMOND_SWORD,
                Material.DIAMOND_HELMET,
                Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_LEGGINGS,
                Material.DIAMOND_BOOTS
        };

        for(final Material mat : diamond) {
            addWithAutoWeight(DIAMOND, mat);
        }

        addMaterialIfExists(DIAMOND, "DIAMOND_HORSE_ARMOR");
        addMaterialIfExists(DIAMOND, "DIAMOND_NAUTILUS_ARMOR");
        addMaterialIfExists(DIAMOND, "DIAMOND_SPEAR");

        // GOLD

        setBaseWeight(GOLD,MAX_WEIGHT - 300);

        final Material[] gold = {
                Material.GOLDEN_AXE,
                Material.GOLDEN_HOE,
                Material.GOLDEN_PICKAXE,
                Material.GOLDEN_SHOVEL,
                Material.GOLDEN_SWORD,
                Material.GOLDEN_HELMET,
                Material.GOLDEN_CHESTPLATE,
                Material.GOLDEN_LEGGINGS,
                Material.GOLDEN_BOOTS
        };

        for(final Material mat : gold) {
            addWithAutoWeight(GOLD, mat);
        }

        addMaterialIfExists(GOLD, "GOLDEN_HORSE_ARMOR");
        addMaterialIfExists(GOLD, "GOLDEN_NAUTILUS_ARMOR");
        addMaterialIfExists(GOLD, "GOLDEN_SPEAR");

        // IRON

        setBaseWeight(IRON,MAX_WEIGHT - 350);

        final Material[] iron = {
                Material.IRON_AXE,
                Material.IRON_HOE,
                Material.IRON_PICKAXE,
                Material.IRON_SHOVEL,
                Material.IRON_SWORD,
                Material.IRON_HELMET,
                Material.IRON_CHESTPLATE,
                Material.IRON_LEGGINGS,
                Material.IRON_BOOTS
        };

        for(final Material mat : iron) {
            addWithAutoWeight(IRON, mat);
        }

        addMaterialIfExists(IRON, "IRON_HORSE_ARMOR");
        addMaterialIfExists(IRON, "IRON_NAUTILUS_ARMOR");
        addMaterialIfExists(IRON, "IRON_SPEAR");

        // CHAINMAIL

        setBaseWeight(CHAINMAIL, MAX_WEIGHT - 400);

        final Material[] chainmail = {
                Material.CHAINMAIL_HELMET,
                Material.CHAINMAIL_CHESTPLATE,
                Material.CHAINMAIL_LEGGINGS,
                Material.CHAINMAIL_BOOTS
        };

        for(final Material mat : chainmail) {
            addWithAutoWeight(CHAINMAIL, mat);
        }

        // COPPER

        setBaseWeight(COPPER,MAX_WEIGHT - 450);

        addMaterialIfExists(COPPER, "COPPER_AXE");
        addMaterialIfExists(COPPER, "COPPER_HOE");
        addMaterialIfExists(COPPER, "COPPER_PICKAXE");
        addMaterialIfExists(COPPER, "COPPER_SHOVEL");
        addMaterialIfExists(COPPER, "COPPER_SWORD");
        addMaterialIfExists(COPPER, "COPPER_HELMET");
        addMaterialIfExists(COPPER, "COPPER_CHESTPLATE");
        addMaterialIfExists(COPPER, "COPPER_LEGGINGS");
        addMaterialIfExists(COPPER, "COPPER_BOOTS");
        addMaterialIfExists(COPPER, "COPPER_HORSE_ARMOR");
        addMaterialIfExists(COPPER, "COPPER_NAUTILUS_ARMOR");
        addMaterialIfExists(COPPER, "COPPER_SPEAR");

        // STONE

        setBaseWeight(STONE,MAX_WEIGHT - 500);

        final Material[] stone = {
                Material.STONE_AXE,
                Material.STONE_HOE,
                Material.STONE_PICKAXE,
                Material.STONE_SHOVEL,
                Material.STONE_SWORD
        };

        for(final Material mat : stone) {
            addWithAutoWeight(STONE, mat);
        }

        // WOOD

        setBaseWeight(WOOD,MAX_WEIGHT - 550);

        final Material[] wood = {
                Material.WOODEN_AXE,
                Material.WOODEN_HOE,
                Material.WOODEN_PICKAXE,
                Material.WOODEN_SHOVEL,
                Material.WOODEN_SWORD,
                Material.SHIELD
        };

        for(final Material mat : wood) {
            addWithAutoWeight(WOOD, mat);
        }

        // LEATHER

        setBaseWeight(LEATHER,MAX_WEIGHT - 600);

        final Material[] leather = {
                Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE,
                Material.LEATHER_LEGGINGS,
                Material.LEATHER_BOOTS
        };

        for(final Material mat : leather) {
            addWithAutoWeight(LEATHER, mat);
        }

        addMaterialIfExists(LEATHER, "LEATHER_HORSE_ARMOR");

        // MISSED

        setBaseWeight(MISC, MAX_WEIGHT - 650);

        addMaterialIfExists(MISC, "ELYTRA");
        addWithAutoWeight(MISC, Material.FLINT_AND_STEEL);
        addWithAutoWeight(MISC, Material.FISHING_ROD);
        addWithAutoWeight(MISC, Material.CARROT_ON_A_STICK);
        addMaterialIfExists(MISC, "WARPED_FUNGUS_ON_A_STICK");
        addMaterialIfExists(MISC, "GOAT_HORN");
        addWithAutoWeight(MISC, Material.LEAD);
        addMaterialIfExists(MISC, "MACE");
        addMaterialIfExists(MISC, "MISSED");
        addWithAutoWeight(MISC, Material.SHEARS);
        addMaterialIfExists(MISC, "TRIDENT");
        addMaterialIfExists(MISC, "TURTLE_HELMET");
        addMaterialIfExists(MISC, "WOLF_ARMOR");
    }

    @Override
    public boolean tryAdd(final Material material, final int weight) {

        for (final Map<Material, Integer> item : subCategories) {
            if (item.containsKey(material)) {
                return true;
            }
        }

        if (material.isItem()) {
            final EquipmentSlot slot = material.getEquipmentSlot();

            if (slot == EquipmentSlot.BODY ||
                    slot == EquipmentSlot.CHEST ||
                    slot == EquipmentSlot.LEGS ||
                    slot == EquipmentSlot.FEET ||
                    slot == EquipmentSlot.HEAD) {
                addWithAutoWeight(MISC, material);
                return true;
            }
        }

        return false;
    }
}
