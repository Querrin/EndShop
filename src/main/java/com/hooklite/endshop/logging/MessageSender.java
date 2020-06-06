package com.hooklite.endshop.logging;

import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageSender {
    public static void toConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(LoggingData.PREFIX + " " + message);
    }

    public static void toPlayer(Player player, String message) {
        player.sendMessage(LoggingData.PREFIX + " " + message);
    }

    public static void buyMessage(Player player, String requirement, String reward) {
        player.sendMessage(String.format("%sSuccessfully bought %s%s%s%s for %s%s", ChatColor.BOLD, ChatColor.GREEN, reward, ChatColor.RESET, ChatColor.BOLD, ChatColor.RED, requirement));
        TransactionLogger.log(player, requirement, reward, Action.BUY);
    }

    public static void sellMessage(Player player, String requirement, String reward) {
        player.sendMessage(String.format("%sSuccessfully sold %s%s%s%s for %s%s", ChatColor.BOLD, ChatColor.RED, requirement, ChatColor.RESET, ChatColor.BOLD, ChatColor.GREEN, reward));
        TransactionLogger.log(player, requirement, reward, Action.SELL);
    }

    public static void noPermission(Player player, String permission) {
        player.sendMessage(String.format("%sYou do not have permission! %s(%s)", ChatColor.RED, ChatColor.RESET, permission));
    }

    public static void noConsoleExecution() {
        System.out.println(ChatColor.AQUA + "You cannot execute this command from console!");
    }
}
