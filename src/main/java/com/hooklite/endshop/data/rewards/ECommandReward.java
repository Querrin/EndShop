package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.config.Transaction;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ECommandReward implements EReward {
    private String reward;

    @Override
    public void executeReward(EItem eItem, Player player, RewardAction action, int amount) {
        // FIXME: Strip of format command of executor
        double price = eItem.buyPrice;
        String command = reward;
        command = command.replace("%player%", player.getName());

        if (action == RewardAction.BUY) {
            if (command.contains("(CONSOLE)")) {
                command = command.replace("(CONSOLE)", "").trim();

                if (Transaction.withdraw(player, price * amount)) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                    MessageLogger.sendBuyMessage(player, eItem.name, price * amount, amount);
                } else {
                    MessageLogger.toPlayer(player, "You do not have enough balance!");
                }
            } else if (command.contains("(PLAYER)")) {
                command = command.replace("(PLAYER)", "").trim();

                if (Transaction.withdraw(player, price * amount)) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                    MessageLogger.sendBuyMessage(player, eItem.name, price * amount, amount);
                } else {
                    MessageLogger.toPlayer(player, "You do not have enough balance!");
                }
            } else {
                // TODO: Better improperly configured command system (Possibly logging)
                MessageLogger.toConsole(String.format("%sCommand executor of \"%s\" is improperly configured!", ChatColor.RED, command));
                MessageLogger.toPlayer(player, String.format("%sCommand executor of \"%s\" is improperly configured!", ChatColor.RED, command));
                MessageLogger.toPlayer(player, "Message the admins to fix the error and get your reward!");
            }
        } else {
            MessageLogger.toConsole(ChatColor.RED + "Commands cannot be sold!");
        }
    }

    @Override
    public void setReward(String reward) {
        this.reward = reward;
    }

    @Override
    public String getReward() {
        return reward;
    }
}
