package com.hooklite.endshop.logging;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageLogger {
    private static final String PREFIX = String.format("%s[%sEndShop%s]%s ", ChatColor.DARK_GRAY, ChatColor.DARK_PURPLE, ChatColor.DARK_GRAY, ChatColor.RESET);

    /**
     * Logs a message to the console with the plugin prefix.
     *
     * @param player  Player which the message is being sent to
     * @param message Message string
     */
    public static void toPlayer(Player player, String message) {
        player.sendMessage(PREFIX + message);
    }

    public static void toConsole(String message) {
        System.out.println(PREFIX + message);
    }
}
