package com.hooklite.endshop.shop;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopItemGui {
    private static Map<Shop, List<Inventory>> shopItemInventories;
    private static ItemStack backItem;
    private static ItemStack nextPageItem;
    private static ItemStack previousPageItem;

    public static void initShopItemInventories(List<Shop> shops) {
        for(Shop shop : shops) {
            initNavigationItems();
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
                else if (itemsAmount < 45)
                    inventorySize = 54;

                Inventory inventory = attachSingleInventoryNavigation(Bukkit.createInventory(null, inventorySize, shop.getTitle()));
                List<Inventory> list = new ArrayList<>();
                list.add(inventory);
                shopItemInventories.put(shop, list);

            }
            else {
                // TODO: Finish loop for inventory navigation attachment
                double inventories = Math.ceil(shop.getShopItems().size() / 45.0);
            }
        }
    }

    private static void initNavigationItems() {
        try {
            ItemStack bItem = new ItemStack(Material.CHEST, 1);
            ItemMeta backMeta = backItem.getItemMeta();
            backMeta.setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
            bItem.setItemMeta(backMeta);
            backItem = bItem;

            ItemStack npItem = new ItemStack(Material.GOLD_NUGGET);
            ItemMeta nextPageMeta = npItem.getItemMeta();
            nextPageMeta.setDisplayName(String.format("%sNext page", ChatColor.GOLD));
            npItem.setItemMeta(nextPageMeta);
            nextPageItem = npItem;

            ItemStack ppItem = new ItemStack(Material.GOLD_NUGGET, 1);
            ItemMeta previousPageMeta = backItem.getItemMeta();
            previousPageMeta.setDisplayName(String.format("%sPrevious page", ChatColor.GOLD));
            ppItem.setItemMeta(previousPageMeta);
            previousPageItem = ppItem;
        } catch (NullPointerException e) {
            MessageLogger.toConsole("Could not initialize navigation items!");
        }
    }


    private static Inventory attachSingleInventoryNavigation(Inventory inventory) {
        ItemStack pagesItem = new ItemStack(Material.BLUE_STAINED_GLASS);
        ItemMeta pagesMeta = pagesItem.getItemMeta();
        pagesMeta.setDisplayName(String.valueOf(ChatColor.DARK_PURPLE) +
                ChatColor.BOLD +
                "Page: " +
                ChatColor.RED +
                "1" +
                ChatColor.GRAY +
                "/" +
                ChatColor.RED +
                "1");
        pagesItem.setItemMeta(pagesMeta);

        int size = inventory.getSize();
        inventory.setItem(size - 9, backItem);
        inventory.setItem(size - 5, pagesItem);

        return inventory;
    }

    private static Inventory attachPagedInventoryNavigation(Inventory inventory, int page) {
        // TODO: Finish function and implement variable page number

        return inventory;
    }
}
