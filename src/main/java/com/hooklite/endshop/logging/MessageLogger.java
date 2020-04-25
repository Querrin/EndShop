package com.hooklite.endshop.logging;

import org.bukkit.entity.Player;

public class MessageLogger {
    public static void toConsole(String message) {
        System.out.println(LoggingData.PREFIX + " " + message);
    }

    public static void toPlayer(Player player, String message) {
        player.sendMessage(LoggingData.PREFIX + " " + message);
    }
}
