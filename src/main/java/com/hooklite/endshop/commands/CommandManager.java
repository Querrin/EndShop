package com.hooklite.endshop.commands;

import com.hooklite.endshop.commands.subcommands.ReloadCommand;
import com.hooklite.endshop.commands.subcommands.SubCommand;
import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.events.ShopMenuOpenEvent;
import com.hooklite.endshop.logging.MessageLogger;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    private final List<SubCommand> subCommands = new ArrayList<>();
    private final List<String> subCommandNames = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new ReloadCommand());

        for (SubCommand subcommand : subCommands) {
            subCommandNames.add(subcommand.getName());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Permission perms = Configuration.getPerms();

        if (command.getName().equalsIgnoreCase("shop")) {
            if (sender instanceof Player)
                Bukkit.getServer().getPluginManager().callEvent(new ShopMenuOpenEvent(((Player) sender).getPlayer()));
            else
                return false;

        } else if (command.getName().equalsIgnoreCase("endshop")) {
            if (sender instanceof Player) {
                MessageLogger.toPlayer((Player) sender, String.format("%sMade by %sQuerrin", ChatColor.LIGHT_PURPLE, ChatColor.RESET));
                MessageLogger.toPlayer((Player) sender, String.format("%sGitHub: %shttps://github.com/Querrin/EndShop", ChatColor.LIGHT_PURPLE, ChatColor.RESET));
                MessageLogger.toPlayer((Player) sender, String.format("%sDiscord: %shttp://discord.hooklite.com", ChatColor.LIGHT_PURPLE, ChatColor.RESET));
            } else {
                MessageLogger.toConsole(String.format("%sMade by %sQuerrin", ChatColor.LIGHT_PURPLE, ChatColor.RESET));
                MessageLogger.toConsole(String.format("%sGitHub: %shttps://github.com/Querrin/EndShop", ChatColor.LIGHT_PURPLE, ChatColor.RESET));
                MessageLogger.toConsole(String.format("%sDiscord: %shttp://discord.hooklite.com", ChatColor.LIGHT_PURPLE, ChatColor.RESET));
            }
        } else if (args.length > 0) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    if (sender instanceof Player && perms.has(sender, subCommand.getPermission())) {
                        subCommand.execute(sender, args);
                    } else if (sender instanceof ConsoleCommandSender) {
                        subCommand.execute(sender, args);
                    } else {
                        MessageLogger.toPlayer((Player) sender, String.format("%sNo permission! %s(%s)", ChatColor.RED, ChatColor.RESET, subCommand.getPermission()));
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            if (args[0].isEmpty()) {
                return subCommandNames;
            } else {
                ArrayList<String> typeCommands = new ArrayList<>();
                for (String s : subCommandNames) {
                    if (s.startsWith(args[0])) {
                        typeCommands.add(s);
                    }
                }
                return typeCommands;
            }
        } else if (args.length > 1) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(args[1])) {
                    return subCommand.getArguments((Player) sender, args);
                }
            }
        }
        return null;
    }
}
