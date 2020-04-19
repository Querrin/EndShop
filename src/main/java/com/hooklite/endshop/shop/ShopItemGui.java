package com.hooklite.endshop.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopItemGui {
    private static Map<Shop, List<Inventory>> shopItemInventories;

    public static void initShopItemInventories(List<Shop> shops) {
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
                else if (itemsAmount < 45)
                    inventorySize = 54;

                Inventory inventory = attachInventoryNavigation(Bukkit.createInventory(null, inventorySize, shop.getTitle()), true);
                List<Inventory> list = new ArrayList<>();
                list.add(inventory);
                shopItemInventories.put(shop, list);

            }
            else {
                double inventories = Math.ceil(shop.getShopItems().size() / 45.0);
            }
        }
    }

    private static Inventory attachInventoryNavigation(Inventory inventory, boolean singlePage) {
        ItemStack backItem = new ItemStack(Material.CHEST, 1);
        ItemMeta backMeta = backItem.getItemMeta();
        backMeta.setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
        backItem.setItemMeta(backMeta);

        if(singlePage) {
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

        ItemStack previousPageItem = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta previousPageMeta = backItem.getItemMeta();
        previousPageMeta.setDisplayName(String.format("%sPrevious page", ChatColor.GOLD));
        previousPageItem.setItemMeta(previousPageMeta);

        ItemStack nextPageItem = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta nextPageMeta = nextPageItem.getItemMeta();
        nextPageMeta.setDisplayName(String.format("%sNext page", ChatColor.GOLD));
        nextPageItem.setItemMeta(nextPageMeta);

        return null;
    }
}
