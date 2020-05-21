package com.hooklite.endshop.data.navigation;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackItem {
    private static final ItemStack BACK_ITEM = new ItemStack(Material.CHEST);

    static {
        ItemMeta meta = BACK_ITEM.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
        BACK_ITEM.setItemMeta(meta);
    }

    public static ItemStack get() {
        return BACK_ITEM;
    }
}
