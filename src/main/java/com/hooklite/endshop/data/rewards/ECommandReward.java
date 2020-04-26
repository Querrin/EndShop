package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ECommandReward implements EReward {
    private String reward;

    @Override
    public void executeReward(Player player, RewardAction action, int amount) {
        // FIXME: Strip of format command of executor
        String command = reward;
        command = command.replace("%player%", player.getName());

        if (command.contains("(CONSOLE)")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        } else if (command.contains("(PLAYER)")) {
            Bukkit.getServer().dispatchCommand(player, reward);
        } else {
            // TODO: Better improperly configured command system (Possibly logging)
            MessageLogger.toConsole(String.format("%sCommand executor of \"%s\" is improperly configured!", ChatColor.RED, command));
            MessageLogger.toPlayer(player, String.format("%sCommand executor of \"%s\" is improperly configured!", ChatColor.RED, command));
            MessageLogger.toPlayer(player, "Message the admins to fix the error and get your reward!");
        }
    }

    @Override
    public void setReward(String reward) {
        this.reward = reward;
    }
}
