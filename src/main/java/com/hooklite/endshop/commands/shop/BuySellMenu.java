package com.hooklite.endshop.commands.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BuySellMenu {
    public Inventory getInventory(ShopItem item) {
        Inventory inventory = Bukkit.createInventory(null, 36, String.format("%sBuy%s/%sSell", ChatColor.GREEN, ChatColor.DARK_GRAY, ChatColor.RED));
        Navigation.attachBuySellNavigation(inventory);

        inventory.setItem(27, getSellItem(item.getSellPrice(), 1));
        inventory.setItem(28, getSellItem(item.getSellPrice() * 16, 16));
        inventory.setItem(29, getSellItem(item.getSellPrice() * 32, 32));
        inventory.setItem(30, getSellItem(item.getSellPrice() * 64, 64));
        inventory.setItem(31, new ItemStack(item.getItem()));
        inventory.setItem(32, getBuyItem(item.getBuyPrice(), 1));
        inventory.setItem(33, getBuyItem(item.getBuyPrice() * 16, 16));
        inventory.setItem(34, getBuyItem(item.getBuyPrice() * 32, 32));
        inventory.setItem(35, getBuyItem(item.getBuyPrice() * 64, 64));

        return inventory;
    }

    private ItemStack getBuyItem(double price, int amount) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format("%s%sBuy %s%sx", ChatColor.GREEN, ChatColor.BOLD, amount, ChatColor.DARK_GRAY));
        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sPrice: %s$%s", ChatColor.DARK_GRAY, ChatColor.GREEN, price));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack getSellItem(double price, int amount) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format("%s%sSell %s%sx", ChatColor.RED, ChatColor.BOLD, amount, ChatColor.DARK_GRAY));
        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sPrice: %s$%s", ChatColor.DARK_GRAY, ChatColor.RED, price));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
