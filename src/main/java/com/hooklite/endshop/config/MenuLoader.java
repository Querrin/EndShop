package com.hooklite.endshop.config;

import com.hooklite.endshop.data.conditions.ERequirement;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuLoader {


    /**
     * Creates a new inventory and loads all the registered shops into it.
     *
     * @param shops  A list of shops that will be loaded.
     * @param player A player object, used for getting the balance.
     * @return An inventory with shops.
     */
    public static Inventory getShopMenu(List<Shop> shops, Player player) {
        Inventory inventory = Bukkit.createInventory(new ShopMenu(), getSize(shops.size()), ChatColor.DARK_GRAY + "Shops");

        // Sets the shop display items
        for(Shop shop : shops) {
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
    public static Inventory getPageMenu(Shop shop, int pageNumber) {
        int inventorySize = getSize(shop.pages.get(pageNumber).getItems().size());
        Inventory inventory = Bukkit.createInventory(new ItemMenu(), inventorySize, shop.title);

        // Sets the inventory items
        for(Item pageItem : shop.pages.get(pageNumber).getItems()) {
            inventory.setItem(pageItem.slot % 45, pageItem.displayItem);
        }

        // Adds navigation elements to the inventory
        if(shop.pages.size() > 1) {
            inventory.setItem(inventorySize - 6, PREVIOUS_PAGE_ITEM);
            inventory.setItem(inventorySize - 4, NEXT_PAGE_ITEM);
        }
        inventory.setItem(inventorySize - 9, BACK_ITEM);
        inventory.setItem(inventorySize - 5, getPageNumberItem(shop, pageNumber));

        return inventory;
    }

    /**
     * Creates an item that shows pages.
     *
     * @param shop   A shop with pages.
     * @param number The page number.
     *               ge     * @return An item that displays the current page.
     */
    private static ItemStack getPageNumberItem(Shop shop, int number) {
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(String.valueOf(ChatColor.RED) +
                (number + 1) +
                ChatColor.DARK_GRAY +
                "/" +
                ChatColor.RED +
                shop.pages.size());

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
        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta meta = item.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(String.format("%sBalance: %s$%.2f", ChatColor.GRAY, ChatColor.GREEN, Configuration.getEcon().getBalance(player)));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getBuyItem(Item eItem, int amount) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sBuy %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));

        return setItemLore(eItem.buyReq, amount, item, meta, eItem.buyReward);
    }

    public static ItemStack getSellItem(Item eItem, int amount) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sSell %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));

        return setItemLore(eItem.sellReq, amount, item, meta, eItem.sellReward);
    }

    public static ItemStack getSellMaxItem(Item eItem, Player player) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sSell MAX", ChatColor.GREEN, ChatColor.BOLD));

        return setItemLore(eItem.sellReq, eItem.sellReq.getMaxAmount(player), item, meta, eItem.sellReward);
    }

    private static ItemStack setItemLore(ERequirement requirement, int amount, ItemStack item, ItemMeta meta, EReward reward) {
        meta.getPersistentDataContainer().set(ItemLoader.AMOUNT_KEY, PersistentDataType.INTEGER, amount);
        List<String> lore = new ArrayList<>();

        lore.add(String.format("%sRequired: %s%s", ChatColor.GRAY, ChatColor.RED, requirement.getName(amount)));
        lore.add(String.format("%sReward: %s%s", ChatColor.GRAY, ChatColor.GREEN, reward.getReward(amount)));

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

}
