package com.hooklite.endshop.commands.shop;

import com.hooklite.endshop.configuration.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ShopGui {
    private static Inventory guiInventory;

    /**
     * Initializes the GUI for shops
     * @param shops A list of shops
     */
    public static void initInventories(List<Shop> shops) {
        int maxInventorySlots = 0;
        int inventorySize;
        for (Shop shop : Configuration.getShops()) {
            if (shop.getSlot() > maxInventorySlots) {
                maxInventorySlots = shop.getSlot();
            }
        }

        if (maxInventorySlots < 9)
            inventorySize = 18;
        else if (maxInventorySlots < 18)
            inventorySize = 27;
        else if (maxInventorySlots < 27)
            inventorySize = 36;
        else if (maxInventorySlots < 36)
            inventorySize = 45;
        else if (maxInventorySlots < 45)
            inventorySize = 54;
        else if (maxInventorySlots < 54)
            inventorySize = 54;
        else
            throw new IllegalArgumentException("Shop slot cannot be bigger than 53! Check your configs!");

        guiInventory = Bukkit.createInventory(null, inventorySize, String.format("%s%sShops", ChatColor.DARK_GRAY, ChatColor.BOLD));

        for (Shop shop : shops) {
            ItemStack displayItem = new ItemStack(shop.getDisplayItem(), 1);
            ItemMeta meta = displayItem.getItemMeta();
            meta.setDisplayName(ChatColor.WHITE + shop.getTitle());
            displayItem.setItemMeta(meta);
            guiInventory.setItem(shop.getSlot(), displayItem);
        }
    }

    /**
     * Returns the GUI which contains all shops.
     * @return Shops inventory
     */
    public static Inventory getInventory() {
        return guiInventory;
    }
}
