package com.hooklite.endshop.commands.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopItemGui {
    private static List<String> shopItemTitles = new ArrayList<>();
    private static List<ShopItem> allShopItems = new ArrayList<>();

    public static List<String> getShopItemTitles() { return shopItemTitles; }
    public static List<ShopItem> getAllShopItems() { return allShopItems; }

    public static void initShopItemInventories(List<Shop> shops) {
        initItemShopTitles(shops);
        initAllShopItems(shops);

        for (Shop shop : shops) {
            int itemsAmount = shop.getShopItems().size();
            int inventorySize;

            if (itemsAmount < 45) {
                if (itemsAmount < 9)
                    inventorySize = 18;
                else if (itemsAmount < 18)
                    inventorySize = 27;
                else if (itemsAmount < 27)
                    inventorySize = 36;
                else if (itemsAmount < 36)
                    inventorySize = 45;
                else
                    inventorySize = 54;

                Inventory inventory = Navigation.attachSingleInventoryNavigation(Bukkit.createInventory(null, inventorySize, String.format("%s%sShops %s> %s%s",
                        ChatColor.DARK_GRAY,
                        ChatColor.BOLD,
                        ChatColor.BLACK,
                        ChatColor.RESET,
                        shop.getTitle())));

                for (ShopItem shopItem : shop.getShopItems()) {
                    ItemStack item = new ItemStack(shopItem.getItem(), 1);
                    ItemMeta meta = item.getItemMeta();

                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(String.format("%sBuy: %s$%s", ChatColor.GRAY, ChatColor.GREEN, shopItem.getBuyPrice()));
                    lore.add(String.format("%sSell: %s$%s", ChatColor.GRAY, ChatColor.RED, shopItem.getSellPrice()));
                    meta.setLore(lore);
                    meta.setDisplayName(shopItem.getName());
                    item.setItemMeta(meta);
                    inventory.addItem(item);
                }

                List<Inventory> inventories = new ArrayList<>();
                inventories.add(inventory);
                shop.setShopItemInventories(inventories);
            } else {
                double inventoryNumber = Math.ceil(shop.getShopItems().size() / 45.0);
                List<Inventory> inventories = new ArrayList<>();

                int j = 0;
                for (int i = 0; i < shops.size(); i++) {
                    Inventory inventory = Navigation.attachPagedInventoryNavigation(Bukkit.createInventory(null, 54, shops.get(i).getTitle()), i + 1, (int) inventoryNumber);
                    inventories.add(inventory);

                    while (j < shops.get(i).getShopItems().size()) {
                        ShopItem shopItem = shops.get(i).getShopItems().get(j);
                        ItemStack item = new ItemStack(shopItem.getItem(), 1);
                        ItemMeta meta = item.getItemMeta();

                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(String.format("%sBuy: %s$%s", ChatColor.GRAY, ChatColor.GREEN, shopItem.getBuyPrice()));
                        lore.add(String.format("%sSell: %s$%s", ChatColor.GRAY, ChatColor.RED, shopItem.getSellPrice()));
                        meta.setLore(lore);
                        meta.setDisplayName(shopItem.getName());
                        item.setItemMeta(meta);
                        inventory.addItem(item);

                        if (j % 44 == 0) {
                            j++;
                            break;
                        }
                        inventory.addItem(item);
                        j++;
                    }
                }
                shop.setShopItemInventories(inventories);
            }
        }
    }

    private static void initItemShopTitles(List<Shop> shops) {
        for (Shop shop : shops) {
            shopItemTitles.add(String.format("%s%sShops %s> %s%s",
                    ChatColor.DARK_GRAY,
                    ChatColor.BOLD,
                    ChatColor.BLACK,
                    ChatColor.RESET,
                    shop.getTitle()));
        }
    }

    private static void initAllShopItems(List<Shop> shops) {
        for(Shop shop : shops) {
            allShopItems.addAll(shop.getShopItems());
        }
    }
}
