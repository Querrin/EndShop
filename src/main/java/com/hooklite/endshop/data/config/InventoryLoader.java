package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryLoader {
    private static final ItemStack BACK_ITEM;
    private static final ItemStack NEXT_PAGE_ITEM;
    private static final ItemStack PREVIOUS_PAGE_ITEM;
    private static ItemStack pageNumberItem;
    private static ItemStack balanceItem;

    static {
        ItemStack backItem = new ItemStack(Material.CHEST, 1);
        ItemMeta backItemMeta = backItem.getItemMeta();
        backItemMeta.setDisplayName("&c&lBack");
        backItem.setItemMeta(backItemMeta);

        ItemStack nextPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        nextPageItemMeta.setDisplayName("&a&lNext page");
        nextPageItem.setItemMeta(nextPageItemMeta);

        ItemStack previousPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta previousPageItemMeta = nextPageItem.getItemMeta();
        previousPageItemMeta.setDisplayName("&a&lPrevious page");
        nextPageItem.setItemMeta(nextPageItemMeta);

        BACK_ITEM = backItem;
        NEXT_PAGE_ITEM = nextPageItem;
        PREVIOUS_PAGE_ITEM = previousPageItem;
    }

    public static Inventory getShopMenu(List<EShop> shops, Player player) {
        Inventory inventory = Bukkit.createInventory(new ShopMenu(), getSize(shops.size()), "&6Shops");

        for (EShop shop : shops) {
            inventory.setItem(shop.slot, new ItemStack(shop.displayItem, 1));
        }

        inventory.setItem(inventory.getSize() - 5, getBalanceItem(player));

        return inventory;
    }

    public static Inventory getPageMenu(EPage page) {
        Inventory inventory = Bukkit.createInventory(new ItemMenu(), getSize(page.getItems().size()));

        return inventory;
    }

    private static ItemStack getPageNumberItem(EShop shop, int number) {
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(String.format("&5%s&8/&5%s", number, shop.pages.size()));
        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack getBalanceItem(Player player) {
        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(String.format("&8Balance: &a%s", VaultLoader.getEcon().getBalance(player)));
        item.setItemMeta(meta);

        return item;
    }

    private static int getSize(int itemAmount) {
        int size = 0;

        if (itemAmount <= 9)
            size = 18;
        else if (itemAmount <= 18)
            size = 27;
        else if (itemAmount <= 27)
            size = 36;
        else if (itemAmount <= 36)
            size = 45;
        else if (itemAmount <= 45)
            size = 54;
        else
            throw new IllegalArgumentException("An inventory cannot contain more that 45 items!");

        return size;
    }
}
