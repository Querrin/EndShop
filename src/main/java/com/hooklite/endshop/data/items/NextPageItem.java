package com.hooklite.endshop.data.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NextPageItem {
    private static final ItemStack NEXT_PAGE_ITEM = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

    static {
        ItemMeta meta = NEXT_PAGE_ITEM.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sNext page", ChatColor.GREEN, ChatColor.BOLD));

        NEXT_PAGE_ITEM.setItemMeta(meta);
    }

    public static ItemStack get() {
        return NEXT_PAGE_ITEM;
    }
}
