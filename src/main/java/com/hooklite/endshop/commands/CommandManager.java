package com.hooklite.endshop.commands;

import com.hooklite.endshop.commands.subcommands.InfoCommand;
import com.hooklite.endshop.commands.subcommands.ReloadCommand;
import com.hooklite.endshop.commands.subcommands.SubCommand;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    private ArrayList<SubCommand> subCommands = new ArrayList<>();


    public CommandManager() {
        subCommands.add(new InfoCommand());
        subCommands.add(new ReloadCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length > 0) {
            if(sender instanceof Player) {
                for(SubCommand subCommand : subCommands) {
                    if(subCommand.getName().equalsIgnoreCase(args[0])) {
                        subCommand.execute((Player) sender, args);
                    }
                }
            }
        }
        else {

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> commands = new ArrayList<>();
            for (int i = 0; i < subCommands.size(); i++) {
                commands.add(subCommands.get(i).getName());
            }

            if (args[0].isEmpty()) {
                return commands;
            }
            else {
                ArrayList<String> typeCommands = new ArrayList<>();
                for(int i = 0; i < commands.size(); i++) {
                    if(commands.get(i).startsWith(args[0])) {
                        typeCommands.add(commands.get(i));
                    }
                }
                return typeCommands;
            }
        }
        else if(args.length == 2) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(args[1])) {
                    return subCommand.getArguments((Player) sender, args);
                }
            }
        }

        return null;
    }
}
