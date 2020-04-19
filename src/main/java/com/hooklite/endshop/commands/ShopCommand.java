package com.hooklite.endshop.commands;

import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import com.hooklite.endshop.shop.Shop;
import com.hooklite.endshop.shop.ShopGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ShopCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            ((Player) commandSender).openInventory(ShopGui.getInventory());
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
