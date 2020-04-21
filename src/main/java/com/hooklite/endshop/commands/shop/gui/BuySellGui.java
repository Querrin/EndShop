package com.hooklite.endshop.commands.shop.gui;

import com.hooklite.endshop.commands.shop.Shop;
import com.hooklite.endshop.commands.shop.ShopItem;
import com.hooklite.endshop.configuration.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BuySellGui {
    public static void initBuySellInventories(List<Shop> shops) {
        for(Shop shop : Configuration.getShops()) {
            List<Inventory> inventories = new ArrayList<>();
            for(ShopItem item : shop.getShopItems()) {
                Inventory inventory = Bukkit.createInventory(null, 36, String.format("%s%s> %s%s", ChatColor.BLACK, ChatColor.BOLD, ChatColor.RESET, item.getName()));
                Navigation.attachBuySellNavigation(inventory);

                inventory.setItem(9, getSellItem(item.getSellPrice() * 64, 64));
                inventory.setItem(10, getSellItem(item.getSellPrice() * 32, 32));
                inventory.setItem(11, getSellItem(item.getSellPrice() * 16, 16));
                inventory.setItem(12, getSellItem(item.getSellPrice(), 1));
                inventory.setItem(13, new ItemStack(item.getItem()));
                inventory.setItem(14, getBuyItem(item.getBuyPrice(), 1));
                inventory.setItem(15, getBuyItem(item.getBuyPrice() * 16, 16));
                inventory.setItem(16, getBuyItem(item.getBuyPrice() * 32, 32));
                inventory.setItem(17, getBuyItem(item.getBuyPrice() * 64, 64));
                inventories.add(inventory);
            }
            shop.setBuySellInventories(inventories);
        }
    }

    private static ItemStack getBuyItem(double price, int amount) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format("%s%sBuy %sx%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, amount));
        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sPrice: %s$%s", ChatColor.GRAY, ChatColor.GREEN, price));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getSellItem(double price, int amount) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format("%s%sSell %sx%s", ChatColor.RED, ChatColor.BOLD, ChatColor.GRAY, amount));
        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sPrice: %s$%s", ChatColor.GRAY, ChatColor.RED, price));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public Inventory getInventory(ShopItem item) {
        Inventory inventory = Bukkit.createInventory(null, 36, String.format("%s%s> %s%s", ChatColor.BLACK, ChatColor.BOLD, ChatColor.RESET, item.getName()));
        Navigation.attachBuySellNavigation(inventory);

        inventory.setItem(9, getSellItem(item.getSellPrice() * 64, 64));
        inventory.setItem(10, getSellItem(item.getSellPrice() * 32, 32));
        inventory.setItem(11, getSellItem(item.getSellPrice() * 16, 16));
        inventory.setItem(12, getSellItem(item.getSellPrice(), 1));
        inventory.setItem(13, new ItemStack(item.getItem()));
        inventory.setItem(14, getBuyItem(item.getBuyPrice(), 1));
        inventory.setItem(15, getBuyItem(item.getBuyPrice() * 16, 16));
        inventory.setItem(16, getBuyItem(item.getBuyPrice() * 32, 32));
        inventory.setItem(17, getBuyItem(item.getBuyPrice() * 64, 64));

        return inventory;
    }
}
