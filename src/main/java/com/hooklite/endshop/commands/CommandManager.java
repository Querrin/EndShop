package com.hooklite.endshop.commands;

import com.hooklite.endshop.Information;
import com.hooklite.endshop.commands.subcommands.AddShopCommand;
import com.hooklite.endshop.commands.subcommands.ReloadCommand;
import com.hooklite.endshop.commands.subcommands.SubCommand;
import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.events.ShopMenuOpenEvent;
import com.hooklite.endshop.logging.MessageSender;
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
        subCommands.add(new AddShopCommand());

        for(SubCommand subcommand : subCommands) {
            subCommandNames.add(subcommand.getName());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Permission perms = Configuration.getPerms();
        Player player = sender instanceof Player ? (Player) sender : null;

        if(command.getName().equalsIgnoreCase("shop")) {
            if(sender instanceof Player)
                if(perms.has(player, "endshop.shop")) {
                    Bukkit.getServer().getPluginManager().callEvent(new ShopMenuOpenEvent(((Player) sender).getPlayer()));
                }
                else {
                    MessageSender.noPermission(player, "endshop.shop");
                }
            else {
                MessageSender.noConsoleExecution();
            }
        }
        else if(args.length > 0 && command.getName().equals("endshop")) {
            for(SubCommand subCommand : subCommands) {
                if(subCommand.getName().equalsIgnoreCase(args[0])) {
                    if(sender instanceof Player) {
                        if(perms.has(player, subCommand.getPermission())) {
                            subCommand.execute(sender, args);
                        }
                        else {
                            MessageSender.noPermission(player, subCommand.getPermission());
                        }
                    }
                    else if(sender instanceof ConsoleCommandSender) {
                        subCommand.execute(sender, args);
                    }
                }
                else {
                    if(subCommand.getName().startsWith(args[0].toLowerCase()) || subCommand.getName().equalsIgnoreCase(args[0])) {
                        String message = String.format("%s\n%sUsage: %s%s", subCommand.getDescription(), ChatColor.LIGHT_PURPLE, ChatColor.RESET, subCommand.getSyntax());

                        if(sender instanceof Player)
                            MessageSender.toPlayer((Player) sender, message);
                        else
                            MessageSender.toConsole(message);
                    }
                    else {
                        String message = "Unknown command.";

                        if(sender instanceof Player)
                            MessageSender.toPlayer((Player) sender, message);
                        else
                            MessageSender.toConsole(message);
                    }
                }
            }
        }
        else if(command.getName().equals("endshop")) {
            for(String str : Information.INFO_MESSAGES) {
                if(sender instanceof Player)
                    if(perms.has(player, "endshop")) {
                        MessageSender.toPlayer(player, str);
                    }
                    else {
                        MessageSender.noPermission(player, "endshop");
                        break;
                    }
                else
                    MessageSender.toConsole(str);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            if(args[0].isEmpty()) {
                return subCommandNames;
            }
            else {
                ArrayList<String> typeCommands = new ArrayList<>();
                for(String s : subCommandNames) {
                    if(s.startsWith(args[0])) {
                        typeCommands.add(s);
                    }
                }
                return typeCommands;
            }
        }
        else if(args.length > 1) {
            for(SubCommand subCommand : subCommands) {
                if(subCommand.getName().equalsIgnoreCase(args[1])) {
                    return subCommand.getArguments((Player) sender, args);
                }
            }
        }
        return null;
    }
}
