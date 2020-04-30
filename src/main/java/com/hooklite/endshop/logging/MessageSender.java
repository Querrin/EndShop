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

    public static void buyMessage(Player player, String requirement, String reward, int amount) {
        player.sendMessage(String.format("%sSuccessfully bought %s%sx%d %s%s%s%s for %s%s", ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY, amount, ChatColor.RESET, reward, ChatColor.RESET, ChatColor.BOLD, ChatColor.RESET, requirement));
    }

    public static void sellMessage(Player player, String requirement, String reward, int amount) {
        player.sendMessage(String.format("%sSuccessfully sold %s%sx%d %s%s%s%s for %s%s", ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY, amount, ChatColor.RESET, requirement, ChatColor.RESET, ChatColor.BOLD, ChatColor.RESET, reward));
    }

    public static void noPermission(Player player, String permission) {
        player.sendMessage(String.format("%sYou do not have permission! %s(%s)", ChatColor.RED, ChatColor.RESET, permission));
    }

    public static void noConsoleExecution() {
        System.out.println(ChatColor.AQUA + "You cannot execute this command from console!");
    }
}
