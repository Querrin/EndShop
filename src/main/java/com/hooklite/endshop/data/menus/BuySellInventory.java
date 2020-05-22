package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.items.BackItem;
import com.hooklite.endshop.data.items.BuyItem;
import com.hooklite.endshop.data.items.SellItem;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.holders.ActionMenuHolder;
import com.hooklite.endshop.holders.PluginHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BuySellInventory implements ActionMenu {

    @Override
    public Inventory get(Item item, PluginHolder previousHolder, Inventory backInventory, Player player) {
        Inventory inventory = Bukkit.createInventory(new ActionMenuHolder(previousHolder, backInventory, item), 36, item.name);

        inventory.setItem(9, SellItem.getMax(item, player));
        inventory.setItem(10, SellItem.get(item, 64));
        inventory.setItem(11, SellItem.get(item, 32));
        inventory.setItem(12, SellItem.get(item, 1));
        inventory.setItem(13, item.displayItem);
        inventory.setItem(14, BuyItem.get(item, 1));
        inventory.setItem(15, BuyItem.get(item, 32));
        inventory.setItem(16, BuyItem.get(item, 64));
        inventory.setItem(17, BuyItem.getMax(item, player));

        inventory.setItem(31, BackItem.get());

        return inventory;
    }

    public static Inventory update(Inventory inventory, Item item, Player player) {
        inventory.setItem(9, SellItem.getMax(item, player));
        inventory.setItem(10, SellItem.get(item, 64));
        inventory.setItem(11, SellItem.get(item, 32));
        inventory.setItem(12, SellItem.get(item, 1));
        inventory.setItem(13, item.displayItem);
        inventory.setItem(14, BuyItem.get(item, 1));
        inventory.setItem(15, BuyItem.get(item, 32));
        inventory.setItem(16, BuyItem.get(item, 64));
        inventory.setItem(17, BuyItem.getMax(item, player));

        inventory.setItem(31, BackItem.get());

        return inventory;
    }
}
