package com.hooklite.endshop.config;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class InventoryFactory {
    public static Inventory getShopMenu(List<Shop> shops, Player player) {
        Inventory inventory = Bukkit.createInventory(new ShopMenu(), getSize(shops.size()), ChatColor.DARK_GRAY + "Shops");

        // Sets the shop display items
        for(Shop shop : shops) {
            inventory.setItem(shop.slot, shop.displayItem);
        }

        inventory.setItem(inventory.getSize() - 5, MenuItemFactory.getBalanceItem(player));

        return inventory;
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
            inventory.setItem(inventorySize - 6, MenuItemFactory.PREVIOUS_PAGE_ITEM);
            inventory.setItem(inventorySize - 4, MenuItemFactory.NEXT_PAGE_ITEM);
        }
        inventory.setItem(inventorySize - 9, MenuItemFactory.BACK_ITEM);
        inventory.setItem(inventorySize - 5, MenuItemFactory.getPageNumberItem(page.getNumber(), pageAmount));

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
