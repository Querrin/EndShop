package com.hooklite.endshop.logging;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerLogger {
    public static void sendMessage(Player player, String message) {
        String prefix = String.format("%s[%sEndShop%s]%s: ",
                ChatColor.DARK_GRAY,
                ChatColor.DARK_PURPLE,
                ChatColor.DARK_GRAY,
                ChatColor.RESET);
        player.sendMessage(prefix + message);
    }
}
