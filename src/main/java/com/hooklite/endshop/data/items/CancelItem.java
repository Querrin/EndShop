package com.hooklite.endshop.data.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CancelItem {
    private static final ItemStack CANCEL_ITEM = new ItemStack(Material.RED_STAINED_GLASS);

    static {
        ItemMeta meta = CANCEL_ITEM.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sCANCEL", ChatColor.RED, ChatColor.BOLD));

        CANCEL_ITEM.setItemMeta(meta);
    }

    public static ItemStack get() {
        return CANCEL_ITEM;
    }
}
