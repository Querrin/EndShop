package com.hooklite.endshop;

import com.hooklite.endshop.commands.Info;
import com.hooklite.endshop.commands.Reload;
import com.hooklite.endshop.commands.Shop;
import com.hooklite.endshop.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {
    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    private int b = 0;


    public CommandManager() {
        if(b > 0) System.out.println(ChatColor.RED + "Bad");
        subCommands.add(new Info());
        subCommands.add(new Reload());
        b++;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(args.length > 0) {
            if(commandSender instanceof Player) {
                for(SubCommand subCommand : subCommands) {
                    if(command.equals(subCommand.getName())) {
                        subCommand.execute((Player) commandSender, args);
                    }
                }
            }
        }
        else {

        }

        return true;
    }
}
