package com.hooklite.endshop.data.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class PageNumberItem {
    private static final ItemStack PAGE_NUMBER_ITEM = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
    private static final ItemMeta PAGE_NUMBER_META = Objects.requireNonNull(PAGE_NUMBER_ITEM.getItemMeta());

    public static ItemStack get(int page, int pages) {
        PAGE_NUMBER_META.setDisplayName(String.valueOf(ChatColor.RED) +
                (page + 1) +
                ChatColor.DARK_GRAY +
                "/" +
                ChatColor.RED +
                pages);

        PAGE_NUMBER_ITEM.setItemMeta(PAGE_NUMBER_META);

        return PAGE_NUMBER_ITEM;
    }
}
