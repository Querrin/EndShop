package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.items.CancelItem;
import com.hooklite.endshop.data.items.ConfirmItem;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.holders.ConfirmMenuHolder;
import com.hooklite.endshop.holders.PluginHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BuyConfirmInventory {
    public Inventory getMenu(Item item, int amount, Action action, Inventory backInventory, PluginHolder previousHolder) {
        Inventory inventory = Bukkit.createInventory(new ConfirmMenuHolder(previousHolder, action, backInventory, item), 45, String.format("%s%sConfirm", ChatColor.DARK_GRAY, ChatColor.BOLD));

        inventory.setItem(22, item.displayItem);

        final ItemStack confirmItem = ConfirmItem.get(amount);
        final ItemStack cancelItem = CancelItem.get();

        inventory.setItem(14, cancelItem);
        inventory.setItem(15, cancelItem);
        inventory.setItem(16, cancelItem);
        inventory.setItem(23, cancelItem);
        inventory.setItem(24, cancelItem);
        inventory.setItem(25, cancelItem);
        inventory.setItem(32, cancelItem);
        inventory.setItem(33, cancelItem);
        inventory.setItem(34, cancelItem);

        inventory.setItem(10, confirmItem);
        inventory.setItem(11, confirmItem);
        inventory.setItem(12, confirmItem);
        inventory.setItem(19, confirmItem);
        inventory.setItem(20, confirmItem);
        inventory.setItem(21, confirmItem);
        inventory.setItem(28, confirmItem);
        inventory.setItem(29, confirmItem);
        inventory.setItem(30, confirmItem);

        return inventory;
    }

}
