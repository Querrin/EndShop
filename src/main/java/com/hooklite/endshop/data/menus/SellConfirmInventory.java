package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.shop.ConfirmMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class SellConfirmInventory {
    public Inventory getMenu(Item item, int amount, Action action) {
        Inventory inventory = Bukkit.createInventory(new ConfirmMenu(action), 45, String.format("%s%sConfirm", ChatColor.DARK_GRAY, ChatColor.BOLD));

        inventory.setItem(22, item.displayItem);

        inventory.setItem(10, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(11, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(12, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(19, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(20, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(21, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(28, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(29, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(30, MenuItemFactory.CANCEL_ITEM);

        inventory.setItem(14, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(15, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(16, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(23, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(24, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(25, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(32, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(33, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(34, MenuItemFactory.getConfirmItem(amount));

        return inventory;
    }

}
