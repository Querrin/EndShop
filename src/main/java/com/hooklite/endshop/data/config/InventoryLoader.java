package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.shop.BuySellMenu;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryLoader {
    private static final ItemStack BACK_ITEM;
    private static final ItemStack NEXT_PAGE_ITEM;
    private static final ItemStack PREVIOUS_PAGE_ITEM;

    static {
        // Loads the static navigation elements

        ItemStack backItem = new ItemStack(Material.CHEST, 1);
        ItemMeta backItemMeta = backItem.getItemMeta();
        Objects.requireNonNull(backItemMeta).setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
        backItem.setItemMeta(backItemMeta);

        ItemStack nextPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        Objects.requireNonNull(nextPageItemMeta).setDisplayName(String.format("%s%sNext page", ChatColor.GREEN, ChatColor.BOLD));
        nextPageItem.setItemMeta(nextPageItemMeta);

        ItemStack previousPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
        Objects.requireNonNull(previousPageItemMeta).setDisplayName(String.format("%s%sPrevious page", ChatColor.GREEN, ChatColor.BOLD));
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

        // Sets the shop display items
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
        int inventorySize = getSize(shop.pages.get(pageNumber).getItems().size());
        Inventory inventory = Bukkit.createInventory(new ItemMenu(), inventorySize, shop.title);

        // Sets the inventory items
        for (EItem item : shop.pages.get(pageNumber).getItems()) {
            inventory.setItem(item.slot, item.displayItem);
        }

        // Adds navigation elements to the inventory
        if (shop.pages.size() > 1) {
            inventory.setItem(inventorySize - 6, PREVIOUS_PAGE_ITEM);
            inventory.setItem(inventorySize - 4, NEXT_PAGE_ITEM);
        }
        inventory.setItem(inventorySize - 9, BACK_ITEM);
        inventory.setItem(inventorySize - 5, getPageNumberItem(shop, pageNumber));

        return inventory;
    }

    public static Inventory getBuySellMenu(EItem item) {
        Inventory inventory = Bukkit.createInventory(new BuySellMenu(), 36, item.name);

        inventory.setItem(9, getSellItem(item.sellPrice * 64, 64));
        inventory.setItem(10, getSellItem(item.sellPrice * 32, 32));
        inventory.setItem(11, getSellItem(item.sellPrice * 16, 16));
        inventory.setItem(12, getSellItem(item.sellPrice, 1));
        inventory.setItem(13, item.displayItem);
        inventory.setItem(14, getBuyItem(item.buyPrice, 1));
        inventory.setItem(15, getBuyItem(item.buyPrice * 16, 16));
        inventory.setItem(16, getBuyItem(item.buyPrice * 32, 32));
        inventory.setItem(17, getBuyItem(item.buyPrice * 64, 64));

        inventory.setItem(30, BACK_ITEM);

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

    private static ItemStack getBuyItem(double price, int amount) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%sBuy %sx%s%s", ChatColor.GREEN, ChatColor.GRAY, ChatColor.GOLD, amount));

        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sPrice: %s$%s", ChatColor.GRAY, ChatColor.GREEN, price));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack getSellItem(double price, int amount) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%Sell %sx%s%s", ChatColor.RED, ChatColor.GRAY, ChatColor.GOLD, amount));

        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sPrice: %s$%s", ChatColor.GRAY, ChatColor.RED, price));
        meta.setLore(lore);
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
