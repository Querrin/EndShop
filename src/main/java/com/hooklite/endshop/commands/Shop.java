package com.hooklite.endshop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Shop implements CommandExecutor, ICommand {
    private static final String NAME = "shop";


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        return true;
    }
}
