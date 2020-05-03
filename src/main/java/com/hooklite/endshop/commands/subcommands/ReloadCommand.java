package com.hooklite.endshop.commands.subcommands;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the configuration files.";
    }

    @Override
    public String getPermission() {
        return "endshop.reload";
    }

    @Override
    public String getSyntax() {
        return "/endshop reload";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Configuration.reloadPlugin();

        if(sender instanceof Player) {
            MessageSender.toPlayer((Player) sender, "Configurations reloaded!");
        }
        else {
            MessageSender.toConsole("Configuration reloaded!");
        }
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {
        return new ArrayList<>();
    }
}
