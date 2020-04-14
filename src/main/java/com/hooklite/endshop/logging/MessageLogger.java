package com.hooklite.endshop.logging;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageLogger {
    private static final String PREFIX = String.format("%s[%sEndShop%s]%s ", ChatColor.DARK_GRAY, ChatColor.DARK_PURPLE, ChatColor.DARK_GRAY, ChatColor.RESET);

    public static void sendMessage(Player player, String message) {
        player.sendMessage(PREFIX + message);
    }

    public static void sendToConsole(String message) {

    }
}
