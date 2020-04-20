package com.hooklite.endshop.shop;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopItemGui {
    private static HashMap<Shop, List<Inventory>> shopItemInventories = new HashMap<>();
    private static ItemStack backItem;
    private static ItemStack nextPageItem;
    private static ItemStack previousPageItem;

    public static Map<Shop, List<Inventory>> getShopItemInventories() { return shopItemInventories; };

    public static void initShopItemInventories(List<Shop> shops) {
        initNavigationItems();
        for(Shop shop : shops) {
            int itemsAmount = shop.getShopItems().size();
            int inventorySize = 0;

            if(itemsAmount < 45) {
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

                Inventory inventory = attachSingleInventoryNavigation(Bukkit.createInventory(null, inventorySize, shop.getTitle()));

                for(ShopItem shopItem : shop.getShopItems()) {
                    ItemStack item = new ItemStack(shopItem.getItem(), 1);
                    ItemMeta meta = item.getItemMeta();

                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(String.format("%sBuy: %s$%s", ChatColor.GRAY, ChatColor.GREEN, shopItem.getBuyPrice()));
                    lore.add(String.format("%sSell: %s$%s", ChatColor.GRAY, ChatColor.GREEN, shopItem.getSellPrice()));
                    meta.setLore(lore);
                    meta.setDisplayName(shopItem.getName());
                    item.setItemMeta(meta);

                    inventory.addItem(item);
                }

                List<Inventory> inventories = new ArrayList<>();
                inventories.add(inventory);
                shopItemInventories.put(shop, inventories);

            }
            else {
                double inventoryNumber = Math.ceil(shop.getShopItems().size() / 45.0);
                List<Inventory> inventories = new ArrayList<>();

                int j = 0;
                for(int i = 0; i < shops.size(); i++) {
                    Inventory inventory = attachPagedInventoryNavigation(Bukkit.createInventory(null, 54, shops.get(i).getTitle()), i + 1, (int) inventoryNumber);
                    inventories.add(inventory);

                    while(j < shops.get(i).getShopItems().size()) {
                        ShopItem shopItem = shops.get(i).getShopItems().get(j);
                        ItemStack item = new ItemStack(shopItem.getItem(), 1);
                        ItemMeta meta = item.getItemMeta();

                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(String.format("%sBuy: %s$%s", ChatColor.GRAY, ChatColor.GREEN, shopItem.getBuyPrice()));
                        lore.add(String.format("%sSell: %s$%s", ChatColor.GRAY, ChatColor.GREEN, shopItem.getSellPrice()));
                        meta.setLore(lore);
                        meta.setDisplayName(shopItem.getName());
                        item.setItemMeta(meta);

                        if(j % 44 == 0) {
                            j++;
                            break;
                        }
                        inventory.addItem(item);
                        j++;
                    }
                }
                shopItemInventories.put(shop, inventories);
            }
        }
    }

    private static void initNavigationItems() {
        try {
            ItemStack bItem = new ItemStack(Material.CHEST, 1);
            ItemMeta backMeta = bItem.getItemMeta();
            backMeta.setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
            bItem.setItemMeta(backMeta);
            backItem = bItem;

            ItemStack npItem = new ItemStack(Material.GOLD_NUGGET);
            ItemMeta nextPageMeta = npItem.getItemMeta();
            nextPageMeta.setDisplayName(String.format("%sNext page", ChatColor.GOLD));
            npItem.setItemMeta(nextPageMeta);
            nextPageItem = npItem;

            ItemStack ppItem = new ItemStack(Material.GOLD_NUGGET, 1);
            ItemMeta previousPageMeta = ppItem.getItemMeta();
            previousPageMeta.setDisplayName(String.format("%sPrevious page", ChatColor.GOLD));
            ppItem.setItemMeta(previousPageMeta);
            previousPageItem = ppItem;
        } catch (NullPointerException e) {
            MessageLogger.toConsole("Could not initialize navigation items!");
        }
    }

    private static ItemStack getPagesItem(int pageNumber, int pages) {
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


    private static Inventory attachSingleInventoryNavigation(Inventory inventory) {
        int size = inventory.getSize();
        inventory.setItem(size - 9, backItem);
        inventory.setItem(size - 5, getPagesItem(1, 1));

        return inventory;
    }

    private static Inventory attachPagedInventoryNavigation(Inventory inventory, int pageNumber, int pages) {
        inventory.setItem(45, backItem);
        inventory.setItem(48, previousPageItem);
        inventory.setItem(49, getPagesItem(pageNumber, pages));
        inventory.setItem(50, nextPageItem);

        return inventory;
    }
}
