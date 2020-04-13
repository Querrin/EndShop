package com.hooklite.endshop.commands;

import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor, ICommand {
    private static final String NAME = "reload";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Configuration.loadConfigs();

        if(sender instanceof Player) {
            MessageLogger.sendMessage((Player) sender, "Configuration reloaded.");
        }
        else {

        }


        return true;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
