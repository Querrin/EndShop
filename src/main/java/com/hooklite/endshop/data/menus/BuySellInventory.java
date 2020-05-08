package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.shop.BuySellMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BuySellInventory implements ActionMenu {

    @Override
    public Inventory getMenu(Item item, Player player) {
        Inventory inventory = Bukkit.createInventory(new BuySellMenu(), 36, item.name);

        inventory.setItem(9, MenuItemFactory.getSellMaxItem(item, player));
        inventory.setItem(10, MenuItemFactory.getSellItem(item, 64));
        inventory.setItem(11, MenuItemFactory.getSellItem(item, 32));
        inventory.setItem(12, MenuItemFactory.getSellItem(item, 1));
        inventory.setItem(13, item.displayItem);
        inventory.setItem(14, MenuItemFactory.getBuyItem(item, 1));
        inventory.setItem(15, MenuItemFactory.getBuyItem(item, 32));
        inventory.setItem(16, MenuItemFactory.getBuyItem(item, 64));
        inventory.setItem(17, MenuItemFactory.getBuyMaxItem(item, player));

        inventory.setItem(31, MenuItemFactory.BACK_ITEM);

        return inventory;
    }
}
