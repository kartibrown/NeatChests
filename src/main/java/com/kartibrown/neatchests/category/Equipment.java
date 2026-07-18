package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class Equipment extends Category {
    private static final int NETHERITE = 0;
    private static final int DIAMOND = 1;
    private static final int GOLD = 2;
    private static final int IRON = 3;
    private static final int COPPER = 4;
    private static final int STONE = 5;
    private static final int WOOD = 6;
    private static final int LEATHER = 7;
    private static final int MISSED = 8;

    public Equipment() {
        super(9);

        // NETHERITE

        final Map<Material, Integer> netherite = createMap(
                Material.NETHERITE_AXE,
                Material.NETHERITE_HOE,
                Material.NETHERITE_PICKAXE,
                Material.NETHERITE_SHOVEL,
                Material.NETHERITE_SWORD,
                Material.NETHERITE_HELMET,
                Material.NETHERITE_CHESTPLATE,
                Material.NETHERITE_LEGGINGS,
                Material.NETHERITE_BOOTS
        );

        addMaterialIfExists(netherite, "NETHERITE_NAUTILUS_ARMOR");
        addMaterialIfExists(netherite, "NETHERITE_SPEAR");

        // DIAMOND

        final Map<Material, Integer> diamond = createMap(
                Material.DIAMOND_AXE,
                Material.DIAMOND_HOE,
                Material.DIAMOND_PICKAXE,
                Material.DIAMOND_SHOVEL,
                Material.DIAMOND_SWORD,
                Material.DIAMOND_HELMET,
                Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_LEGGINGS,
                Material.DIAMOND_BOOTS
        );

        addMaterialIfExists(diamond, "DIAMOND_NAUTILUS_ARMOR");
        addMaterialIfExists(diamond, "DIAMOND_SPEAR");

        // GOLD

        final Map<Material, Integer> gold = createMap(
                Material.GOLDEN_AXE,
                Material.GOLDEN_HOE,
                Material.GOLDEN_PICKAXE,
                Material.GOLDEN_SHOVEL,
                Material.GOLDEN_SWORD,
                Material.GOLDEN_HELMET,
                Material.GOLDEN_CHESTPLATE,
                Material.GOLDEN_LEGGINGS,
                Material.GOLDEN_BOOTS
        );

        addMaterialIfExists(gold, "GOLDEN_NAUTILUS_ARMOR");
        addMaterialIfExists(gold, "GOLDEN_SPEAR");

        // IRON

        final Map<Material, Integer> iron = createMap(
                Material.IRON_AXE,
                Material.IRON_HOE,
                Material.IRON_PICKAXE,
                Material.IRON_SHOVEL,
                Material.IRON_SWORD,
                Material.IRON_HELMET,
                Material.IRON_CHESTPLATE,
                Material.IRON_LEGGINGS,
                Material.IRON_BOOTS
        );

        addMaterialIfExists(iron, "IRON_NAUTILUS_ARMOR");
        addMaterialIfExists(iron, "IRON_SPEAR");

        // COPPER

        final Map<Material, Integer> copper = new EnumMap<>(Material.class);
        addMaterialIfExists(copper, "COPPER_AXE");
        addMaterialIfExists(copper, "COPPER_HOE");
        addMaterialIfExists(copper, "COPPER_PICKAXE");
        addMaterialIfExists(copper, "COPPER_SHOVEL");
        addMaterialIfExists(copper, "COPPER_SWORD");
        addMaterialIfExists(copper, "COPPER_HELMET");
        addMaterialIfExists(copper, "COPPER_CHESTPLATE");
        addMaterialIfExists(copper, "COPPER_LEGGINGS");
        addMaterialIfExists(copper, "COPPER_BOOTS");
        addMaterialIfExists(copper, "COPPER_NAUTILUS_ARMOR");
        addMaterialIfExists(copper, "COPPER_SPEAR");

        // STONE

        final Map<Material, Integer> stone = createMap(
                Material.STONE_AXE,
                Material.STONE_HOE,
                Material.STONE_PICKAXE,
                Material.STONE_SHOVEL,
                Material.STONE_SWORD
        );

        // WOOD

        final Map<Material, Integer> wood = createMap(
                Material.WOODEN_AXE,
                Material.WOODEN_HOE,
                Material.WOODEN_PICKAXE,
                Material.WOODEN_SHOVEL,
                Material.WOODEN_SWORD
        );

        // LEATHER

        final Map<Material, Integer> leather = createMap(
                Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE,
                Material.LEATHER_LEGGINGS,
                Material.LEATHER_BOOTS
        );

        items[NETHERITE].putAll(netherite);
        items[DIAMOND].putAll(diamond);
        items[GOLD].putAll(gold);
        items[IRON].putAll(iron);
        items[COPPER].putAll(copper);
        items[STONE].putAll(stone);
        items[WOOD].putAll(wood);
        items[LEATHER].putAll(leather);
    }

    @Override
    public boolean tryAdd(final Material material) {

        for (final Map<Material, Integer> item : items) {
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
                addToCategory(MISSED, material);
                return true;
            }
        }

        return false;
    }
}
