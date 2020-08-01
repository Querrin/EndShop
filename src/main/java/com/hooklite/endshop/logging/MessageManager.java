package com.hooklite.endshop.logging;

import com.hooklite.endshop.config.DefaultProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageManager {
    private static final String PREFIX = String.format("%s[%sEnd%sShop%s]%s ", ChatColor.DARK_GRAY, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.DARK_GRAY, ChatColor.RESET);

    public static void logError(String message) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.RED + message);
    }

    public static void logDebug(String message) {
        if(DefaultProvider.getConfig().getBoolean("debug", false))
            Bukkit.getConsoleSender().sendMessage(PREFIX + ChatColor.AQUA + message);
    }
}
