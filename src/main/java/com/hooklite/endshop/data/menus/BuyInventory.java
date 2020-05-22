package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.items.BackItem;
import com.hooklite.endshop.data.items.BuyItem;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.holders.ActionMenuHolder;
import com.hooklite.endshop.holders.PluginHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BuyInventory implements ActionMenu {

    @Override
    public Inventory get(Item item, PluginHolder previousHolder, Inventory backInventory, Player player) {
        Inventory inventory = Bukkit.createInventory(new ActionMenuHolder(previousHolder, backInventory, item), 45, item.name);

        inventory.setItem(13, item.displayItem);

        inventory.setItem(19, BuyItem.get(item, 1));
        inventory.setItem(20, BuyItem.get(item, 16));
        inventory.setItem(21, BuyItem.get(item, 32));
        inventory.setItem(22, BuyItem.get(item, 64));
        inventory.setItem(23, BuyItem.get(item, 128));
        inventory.setItem(24, BuyItem.get(item, 256));
        inventory.setItem(25, BuyItem.getMax(item, player));

        inventory.setItem(40, BackItem.get());

        return inventory;
    }

    public static Inventory update(Inventory inventory, Item item, Player player) {
        inventory.setItem(13, item.displayItem);

        inventory.setItem(19, BuyItem.get(item, 1));
        inventory.setItem(20, BuyItem.get(item, 16));
        inventory.setItem(21, BuyItem.get(item, 32));
        inventory.setItem(22, BuyItem.get(item, 64));
        inventory.setItem(23, BuyItem.get(item, 128));
        inventory.setItem(24, BuyItem.get(item, 256));
        inventory.setItem(25, BuyItem.getMax(item, player));

        inventory.setItem(40, BackItem.get());

        return inventory;
    }
}
