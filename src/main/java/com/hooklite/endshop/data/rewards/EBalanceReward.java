package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.logging.MessageLogger;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EBalanceReward implements EReward {
    private double reward;

    @Override
    public void executeReward(EItem eItem, Player player, RewardAction action, int amount) {
        double price = eItem.buyPrice;
        ItemStack item = new ItemStack(eItem.displayItem.getType(), 1);
        Economy econ = Configuration.getEcon();

        if (action == RewardAction.BUY) {
            econ.withdrawPlayer(player, price * amount);
            econ.depositPlayer(player, reward * amount);

            MessageLogger.sendBuyMessage(player, eItem.name, price * amount, amount);
        } else {
            if (player.getInventory().containsAtLeast(item, amount)) {

                for (int i = 0; i < amount; i++) {
                    player.getInventory().removeItem(item);
                }

                econ.depositPlayer(player, reward * amount);
                MessageLogger.sendSellMessage(player, eItem.name, String.format("%s$%s", ChatColor.GREEN, reward * amount), amount);
            } else {
                MessageLogger.toPlayer(player, "You do not have enough items to sell!");
            }
        }
    }

    @Override
    public void setReward(String reward) {
        this.reward = Double.parseDouble(reward);
    }

    @Override
    public String getReward() {
        return Double.toString(reward);
    }
}
