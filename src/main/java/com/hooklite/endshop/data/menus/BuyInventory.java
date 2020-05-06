package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.shop.BuySellMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BuyInventory implements ActionMenu {

    @Override
    public Inventory getMenu(Item item, Player player) {
        Inventory inventory = Bukkit.createInventory(new BuySellMenu(), 45, item.name);

        inventory.setItem(13, item.displayItem);

        inventory.setItem(19, MenuItemFactory.getBuyItem(item, 1));
        inventory.setItem(20, MenuItemFactory.getBuyItem(item, 16));
        inventory.setItem(21, MenuItemFactory.getBuyItem(item, 32));
        inventory.setItem(22, MenuItemFactory.getBuyItem(item, 64));
        inventory.setItem(23, MenuItemFactory.getBuyItem(item, 128));
        inventory.setItem(24, MenuItemFactory.getBuyItem(item, 256));
        inventory.setItem(25, MenuItemFactory.getBuyMaxItem(item, player));

        inventory.setItem(40, MenuItemFactory.BACK_ITEM);

        return inventory;
    }
}
