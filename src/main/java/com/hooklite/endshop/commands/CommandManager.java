package com.hooklite.endshop.commands;

import com.hooklite.endshop.commands.subcommands.ReloadCommand;
import com.hooklite.endshop.commands.subcommands.SubCommand;
import com.hooklite.endshop.events.ShopMenuOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
        if (command.getName().equalsIgnoreCase("shop")) {
            if (sender instanceof Player) {
                Bukkit.getServer().getPluginManager().callEvent(new ShopMenuOpenEvent(((Player) sender).getPlayer()));
            }
        } else if (args.length > 0) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    subCommand.execute(sender, args);
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
