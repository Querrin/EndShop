package com.hooklite.endshop.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Inventory shops = Bukkit.createInventory(null, 27, "Shops");
        Player player = (Player) commandSender;

        shops.addItem(new ItemStack(Material.ACACIA_DOOR, 3));
        player.openInventory(shops);

        return true;
    }
}
