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
    private int b = 0;


    public CommandManager() {
        if(b > 0) System.out.println(ChatColor.RED + "Bad");
        subCommands.add(new InfoCommand());
        subCommands.add(new ReloadCommand());
        b++;
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
        for(String arg : args) MessageLogger.sendMessage((Player) sender, arg);
        MessageLogger.sendMessage((Player) sender , Integer.toString(args.length));

        if (args.length == 1) {
            ArrayList<String> commands = new ArrayList<>();
            for (int i = 0; i < subCommands.size(); i++) {
                commands.add(subCommands.get(i).getName());
            }
            return commands;
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
