package com.kartibrown.neatchests.category;

import com.kartibrown.neatchests.Category;
import org.bukkit.Material;

// FALLBACK CLASS
public final class Misc extends Category {
    public Misc() {
        super(1);
    }

    @Override
    public boolean tryAdd(final Material material) {
        // I don't want to explicitly claim anything.
        // Let the other specialized categories have first choice!
        return false;
    }

    @Override
    public void add(final Material material) {
        // The SortingManager couldn't find a home for this block,
        // so it drops here and gets a default sorting weight.
        addToCategory(0, material, 0);
    }
}
