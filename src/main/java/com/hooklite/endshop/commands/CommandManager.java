package com.hooklite.endshop.commands;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class CommandManager implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("shop")) {
                Inventory inventory = Bukkit.createInventory(null, 9, "Hello!");
                MessageLogger.toPlayer((Player) sender, inventory.getClass().toString());
                ((Player) sender).openInventory(Bukkit.createInventory(null, 9, "Hello!"));
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
