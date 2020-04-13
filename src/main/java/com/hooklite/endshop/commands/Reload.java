package com.hooklite.endshop.commands;

import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements SubCommand {
    private static final String NAME = "reload";
    private static final String DESCRIPTION = "Reloads the configuration files.";
    private static final String SYNTAX = "/es reload";

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESCRIPTION; }

    @Override
    public String getSyntax() { return SYNTAX; }

    @Override
    public void execute(Player player, String[] args) {

    }
}
