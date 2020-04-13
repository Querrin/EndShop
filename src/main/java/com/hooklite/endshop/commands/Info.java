package com.hooklite.endshop.commands;

import org.bukkit.entity.Player;

public class Info implements SubCommand {
    public static final String NAME = "info";
    public static final String DESCRIPTION = "Displays information about the plugin.";
    public static final String SYNTAX = "/es info";


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
