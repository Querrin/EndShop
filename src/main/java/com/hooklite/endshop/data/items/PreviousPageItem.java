package com.hooklite.endshop.data.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PreviousPageItem {
    private static final ItemStack PREVIOUS_PAGE_ITEM = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

    static {
        ItemMeta meta = PREVIOUS_PAGE_ITEM.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sPrevious page", ChatColor.GREEN, ChatColor.BOLD));

        PREVIOUS_PAGE_ITEM.setItemMeta(meta);
    }

    public static ItemStack get() {
        return PREVIOUS_PAGE_ITEM;
    }
}
