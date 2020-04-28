package com.hooklite.endshop.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {
    String getName();

    String getDescription();

    String getSyntax();

    void execute(CommandSender sender, String[] args);

    List<String> getArguments(Player player, String[] args);
}
