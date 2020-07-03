package com.hooklite.endshop;

import com.hooklite.endshop.configuration.DefaultProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageSender {
    private static final String CONSOLE_PREFIX = String.format("%s%s[%sEndShop%s%s]%s", ChatColor.GRAY, ChatColor.BOLD, ChatColor.LIGHT_PURPLE, ChatColor.GRAY, ChatColor.BOLD, ChatColor.RESET);

    public static void toConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(CONSOLE_PREFIX + " " + message);
    }

    public static void logDebug(String message) {
        if(DefaultProvider.debug())
            Bukkit.getConsoleSender().sendMessage(CONSOLE_PREFIX + " " + message);
    }

    public static void logError(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + message);
    }

    public static void printStackTrace(Exception e) {
        Bukkit.getConsoleSender().sendMessage(CONSOLE_PREFIX + ChatColor.RED + " An error has occurred.");
        e.printStackTrace();
    }
}
