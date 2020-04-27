package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        backItemMeta.setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
        backItem.setItemMeta(backItemMeta);

        ItemStack nextPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        nextPageItemMeta.setDisplayName(String.format("%s%sNext page", ChatColor.GREEN, ChatColor.BOLD));
        nextPageItem.setItemMeta(nextPageItemMeta);

        ItemStack previousPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta previousPageItemMeta = nextPageItem.getItemMeta();
        previousPageItemMeta.setDisplayName(String.format("%s%sPrevious page", ChatColor.GREEN, ChatColor.BOLD));
        previousPageItem.setItemMeta(previousPageItemMeta);

        BACK_ITEM = backItem;
        NEXT_PAGE_ITEM = nextPageItem;
        PREVIOUS_PAGE_ITEM = previousPageItem;
    }

    public static ItemStack getBackItem() {
        return BACK_ITEM;
    }

    public static ItemStack getNextPageItem() {
        return NEXT_PAGE_ITEM;
    }

    public static ItemStack getPreviousPageItem() {
        return PREVIOUS_PAGE_ITEM;
    }

    /**
     * Creates a new inventory and loads all the registered shops into it.
     *
     * @param shops  A list of shops that will be loaded.
     * @param player A player object, used for getting the balance.
     * @return An inventory with shops.
     */
    public static Inventory getShopMenu(List<EShop> shops, Player player) {
        Inventory inventory = Bukkit.createInventory(new ShopMenu(), getSize(shops.size()), ChatColor.DARK_GRAY + "Shops");

        for (EShop shop : shops) {
            inventory.setItem(shop.slot, shop.displayItem);
        }

        inventory.setItem(inventory.getSize() - 5, getBalanceItem(player));

        return inventory;
    }

    /**
     * Creates a new inventory with items from the page given.
     *
     * @param shop       An instance of EShop.
     * @param pageNumber The page number.
     * @return A list of inventories with navigation and items.
     */
    public static Inventory getPageMenu(EShop shop, int pageNumber) {
        // TODO: Single page no navigation check

        int inventorySize = getSize(shop.pages.get(pageNumber).getItems().size());
        Inventory inventory = Bukkit.createInventory(new ItemMenu(), inventorySize, shop.title);

        for (EItem item : shop.pages.get(pageNumber).getItems()) {
            inventory.setItem(item.slot, item.displayItem);
        }

        inventory.setItem(inventorySize - 9, BACK_ITEM);
        inventory.setItem(inventorySize - 6, PREVIOUS_PAGE_ITEM);
        inventory.setItem(inventorySize - 5, getPageNumberItem(shop, pageNumber));
        inventory.setItem(inventorySize - 4, NEXT_PAGE_ITEM);

        return inventory;
    }

    /**
     * Creates an item that shows pages.
     *
     * @param shop   A shop with pages.
     * @param number The page number.
     * @return An item that displays the current page.
     */
    private static ItemStack getPageNumberItem(EShop shop, int number) {
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();

        // TODO: Simplify display name
        meta.setDisplayName(String.format("%s%s%s/%s%s", ChatColor.RED, number + 1, ChatColor.DARK_GRAY, ChatColor.RED, shop.pages.size()));
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Creates an item that shows balance.
     *
     * @param player The player which the balance will be displayed from.
     * @return An item that displays the player's balance.
     */
    private static ItemStack getBalanceItem(Player player) {
        // TODO: Properly display big numbers
        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(String.format("%sBalance: %s$%s", ChatColor.GRAY, ChatColor.GREEN, Configuration.getEcon().getBalance(player)));
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Gets the size of an inventory depending on the amount of items.
     *
     * @param itemAmount Amount of items.
     * @return A integer that represents the amount of slots in an inventory.
     */
    private static int getSize(int itemAmount) {
        int size;

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
