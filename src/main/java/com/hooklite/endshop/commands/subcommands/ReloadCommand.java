package com.hooklite.endshop.commands.subcommands;

import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ReloadCommand implements SubCommand {
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
        Configuration.loadConfigs();
        MessageLogger.sendMessage(player, "Configurations reloaded!");
    }

    @Override
    public ArrayList<String> getArguments(Player player, String[] args) { return null; }
}
