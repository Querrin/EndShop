package com.hooklite.endshop.commands;

import com.hooklite.endshop.EndShop;
import org.bukkit.command.CommandExecutor;

import java.util.ArrayList;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class Commands {
    private static ArrayList<ICommand> commands = new ArrayList<>();

    public static void registerCommands() {
        for(ICommand executor : commands) {
            getPlugin(EndShop.class)
                    .getCommand(executor.getName())
                    .setExecutor((CommandExecutor) executor);
        }
    }

    public static void addCommandExecutor(ICommand executor) {
        commands.add(executor);
    }

    public static void addCommandExecutor(ICommand[] array) {
        for(ICommand executor : array) {
            commands.add(executor);
        }
    }
}
