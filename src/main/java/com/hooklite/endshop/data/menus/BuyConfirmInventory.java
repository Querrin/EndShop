package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.shop.ConfirmMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class BuyConfirmInventory {
    public Inventory getMenu(Item item, int amount, Action action) {
        Inventory inventory = Bukkit.createInventory(new ConfirmMenu(action), 45, String.format("%s%sConfirm", ChatColor.DARK_GRAY, ChatColor.BOLD));

        inventory.setItem(22, item.displayItem);

        inventory.setItem(14, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(15, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(16, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(23, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(24, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(25, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(32, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(33, MenuItemFactory.CANCEL_ITEM);
        inventory.setItem(34, MenuItemFactory.CANCEL_ITEM);

        inventory.setItem(10, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(11, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(12, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(19, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(20, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(21, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(28, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(29, MenuItemFactory.getConfirmItem(amount));
        inventory.setItem(30, MenuItemFactory.getConfirmItem(amount));

        return inventory;
    }

}
