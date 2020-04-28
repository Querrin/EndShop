package com.hooklite.endshop.logging;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageSender {
    public static void toConsole(String message) {
        System.out.println(LoggingData.PREFIX + " " + message);
    }

    public static void toPlayer(Player player, String message) {
        player.sendMessage(LoggingData.PREFIX + " " + message);
    }

    public static void buyMessage(Player player, String item, double price, int amount) {
        player.sendMessage(String.format("%sSuccessfully bought %s%sx%s %s for %s$%s", ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY, amount, item, ChatColor.GREEN, price));
    }

    public static void sellMessage(Player player, String item, String reward, int amount) {
        player.sendMessage(String.format("%sSuccessfully sold %s%sx%s %s %sfor %s", ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY, amount, item, ChatColor.RESET, reward));
    }

    public static void noPermission(Player player, String permission) {
        player.sendMessage(String.format("%sYou do not have permission! %s(%s)", ChatColor.RED, ChatColor.RESET, permission));
    }
}
