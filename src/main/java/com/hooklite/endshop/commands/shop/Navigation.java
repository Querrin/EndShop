package com.hooklite.endshop.commands.shop;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Navigation {
    private static ItemStack backItem = new ItemStack(Material.CHEST, 1);
    private static ItemStack nextPageItem = new ItemStack(Material.IRON_NUGGET, 1);
    private static ItemStack previousPageItem = new ItemStack(Material.IRON_NUGGET, 1);

    public static ItemStack getBackItem() { return backItem; }
    public static ItemStack getNextPageItem() { return nextPageItem; }
    public static ItemStack getPreviousPageItem() { return previousPageItem; }

    static {
        try {
            ItemStack bItem = new ItemStack(Material.CHEST, 1);
            ItemMeta backMeta = bItem.getItemMeta();
            backMeta.setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
            bItem.setItemMeta(backMeta);
            backItem = bItem;

            ItemStack npItem = new ItemStack(Material.IRON_NUGGET);
            ItemMeta nextPageMeta = npItem.getItemMeta();
            nextPageMeta.setDisplayName(String.format("%sNext page", ChatColor.GOLD));
            npItem.setItemMeta(nextPageMeta);
            nextPageItem = npItem;

            ItemStack ppItem = new ItemStack(Material.IRON_NUGGET, 1);
            ItemMeta previousPageMeta = ppItem.getItemMeta();
            previousPageMeta.setDisplayName(String.format("%sPrevious page", ChatColor.GOLD));
            ppItem.setItemMeta(previousPageMeta);
            previousPageItem = ppItem;

            // TODO: Add balance golden nugget item

        } catch (NullPointerException e) {
            MessageLogger.toConsole("Could not initialize navigation items!");
        }
    }

    static ItemStack getPagesItem(int pageNumber, int pages) {
        ItemStack pagesItem = new ItemStack(Material.BLUE_STAINED_GLASS);
        ItemMeta pagesMeta = pagesItem.getItemMeta();
        pagesMeta.setDisplayName(String.valueOf(ChatColor.DARK_PURPLE) +
                ChatColor.BOLD +
                "Page: " +
                ChatColor.RED +
                pageNumber +
                ChatColor.GRAY +
                "/" +
                ChatColor.RED +
                pageNumber);
        pagesItem.setItemMeta(pagesMeta);

        return pagesItem;
    }

    static Inventory attachSingleInventoryNavigation(Inventory inventory) {
        int size = inventory.getSize();
        inventory.setItem(size - 9, backItem);
        inventory.setItem(size - 5, getPagesItem(1, 1));

        return inventory;
    }

    static Inventory attachPagedInventoryNavigation(Inventory inventory, int pageNumber, int pages) {
        inventory.setItem(45, backItem);
        inventory.setItem(48, previousPageItem);
        inventory.setItem(49, getPagesItem(pageNumber, pages));
        inventory.setItem(50, nextPageItem);

        return inventory;
    }

    static Inventory attachBuySellNavigation(Inventory inventory) {
        inventory.setItem(inventory.getSize() - 5, backItem);
        return inventory;
    }
}
