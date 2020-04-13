package com.hooklite.endshop.commands;

import org.bukkit.entity.Player;

public interface SubCommand {
    public String getName();
    public String getDescription();
    public String getSyntax();
    public void execute(Player player, String[] args);
}
