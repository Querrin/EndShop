package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.items.CancelItem;
import com.hooklite.endshop.data.items.ConfirmItem;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.holders.ConfirmMenuHolder;
import com.hooklite.endshop.holders.PluginHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellConfirmInventory {
    public Inventory getMenu(Item item, int amount, Action action, Inventory backInventory, PluginHolder previousHolder) {
        Inventory inventory = Bukkit.createInventory(new ConfirmMenuHolder(previousHolder, action, backInventory, item), 45, String.format("%s%sConfirm", ChatColor.DARK_GRAY, ChatColor.BOLD));

        inventory.setItem(22, item.displayItem);

        final ItemStack confirmItem = ConfirmItem.get(amount);
        final ItemStack cancelItem = CancelItem.get();

        inventory.setItem(10, cancelItem);
        inventory.setItem(11, cancelItem);
        inventory.setItem(12, cancelItem);
        inventory.setItem(19, cancelItem);
        inventory.setItem(20, cancelItem);
        inventory.setItem(21, cancelItem);
        inventory.setItem(28, cancelItem);
        inventory.setItem(29, cancelItem);
        inventory.setItem(30, cancelItem);

        inventory.setItem(14, confirmItem);
        inventory.setItem(15, confirmItem);
        inventory.setItem(16, confirmItem);
        inventory.setItem(23, confirmItem);
        inventory.setItem(24, confirmItem);
        inventory.setItem(25, confirmItem);
        inventory.setItem(32, confirmItem);
        inventory.setItem(33, confirmItem);
        inventory.setItem(34, confirmItem);

        return inventory;
    }
}
