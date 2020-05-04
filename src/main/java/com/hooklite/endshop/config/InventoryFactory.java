package com.hooklite.endshop.config;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.shop.ItemMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class InventoryFactory {
    public static final ItemStack BACK_ITEM;
    public static final ItemStack NEXT_PAGE_ITEM;
    public static final ItemStack PREVIOUS_PAGE_ITEM;

    static {
        // Loads the static navigation elements
        ItemStack backItem = new ItemStack(Material.CHEST, 1);
        ItemMeta backItemMeta = backItem.getItemMeta();
        Objects.requireNonNull(backItemMeta).setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
        backItem.setItemMeta(backItemMeta);

        ItemStack nextPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        Objects.requireNonNull(nextPageItemMeta).setDisplayName(String.format("%s%sNext page", ChatColor.GREEN, ChatColor.BOLD));
        nextPageItem.setItemMeta(nextPageItemMeta);

        ItemStack previousPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
        Objects.requireNonNull(previousPageItemMeta).setDisplayName(String.format("%s%sPrevious page", ChatColor.GREEN, ChatColor.BOLD));
        previousPageItem.setItemMeta(previousPageItemMeta);

        BACK_ITEM = backItem;
        NEXT_PAGE_ITEM = nextPageItem;
        PREVIOUS_PAGE_ITEM = previousPageItem;
    }

    private static ItemStack getPageNumberItem(int page, int pages) {
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.valueOf(ChatColor.RED) +
                (page + 1) +
                ChatColor.DARK_GRAY +
                "/" +
                ChatColor.RED +
                pages);

        item.setItemMeta(meta);

        return item;
    }

    public static Inventory getPageInventory(Shop shop, Page page, int pageAmount) {
        int inventorySize = getSize(page.getItems().size());
        Inventory inventory = Bukkit.createInventory(new ItemMenu(), inventorySize, shop.title);

        // Sets the inventory items
        for(Item pageItem : page.getItems()) {
            inventory.setItem(pageItem.slot % 45, pageItem.displayItem);
        }

        // Adds navigation elements to the inventory
        if(pageAmount > 1) {
            inventory.setItem(inventorySize - 6, InventoryFactory.PREVIOUS_PAGE_ITEM);
            inventory.setItem(inventorySize - 4, InventoryFactory.NEXT_PAGE_ITEM);
        }
        inventory.setItem(inventorySize - 9, InventoryFactory.BACK_ITEM);
        inventory.setItem(inventorySize - 5, getPageNumberItem(page.getNumber(), pageAmount));

        return inventory;
    }

    private static int getSize(int itemAmount) {
        int size;

        if(itemAmount <= 9)
            size = 18;
        else if(itemAmount <= 18)
            size = 27;
        else if(itemAmount <= 27)
            size = 36;
        else if(itemAmount <= 36)
            size = 45;
        else if(itemAmount <= 45)
            size = 54;
        else
            throw new IllegalArgumentException("An inventory cannot contain more that 45 items!");

        return size;
    }
}
