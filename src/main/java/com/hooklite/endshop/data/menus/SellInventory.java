package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.holders.ActionMenuHolder;
import com.hooklite.endshop.holders.PluginHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SellInventory implements ActionMenu {

    @Override
    public Inventory getMenu(Item item, PluginHolder previousHolder, Player player) {
        Inventory inventory = Bukkit.createInventory(new ActionMenuHolder(previousHolder), 45, item.name);

        inventory.setItem(13, item.displayItem);

        inventory.setItem(19, MenuItemFactory.getSellItem(item, 1));
        inventory.setItem(20, MenuItemFactory.getSellItem(item, 16));
        inventory.setItem(21, MenuItemFactory.getSellItem(item, 32));
        inventory.setItem(22, MenuItemFactory.getSellItem(item, 64));
        inventory.setItem(23, MenuItemFactory.getSellItem(item, 128));
        inventory.setItem(24, MenuItemFactory.getSellItem(item, 256));
        inventory.setItem(25, MenuItemFactory.getSellMaxItem(item, player));

        inventory.setItem(40, MenuItemFactory.BACK_ITEM);

        return inventory;
    }
}