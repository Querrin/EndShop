package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.shop.ConfirmMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class ConfirmInventory {
    public Inventory getMenu(Item item) {
        Inventory inventory = Bukkit.createInventory(new ConfirmMenu(), 45, String.format("%s%sCONFIRM", ChatColor.GREEN, ChatColor.BOLD));

        inventory.setItem(20, item.displayItem);

        inventory.setItem(10, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(11, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(12, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(18, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(19, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(28, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(29, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(30, MenuItemFactory.CANCEL_ITEM);

        inventory.setItem(14, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(15, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(16, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(21, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(22, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(23, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(32, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(33, MenuItemFactory.CONFIRM_ITEM);
        inventory.setItem(34, MenuItemFactory.CONFIRM_ITEM);

        return inventory;
    }
}
