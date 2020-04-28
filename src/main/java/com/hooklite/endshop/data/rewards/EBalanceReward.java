package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.config.Transaction;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EBalanceReward implements EReward {
    private double reward;

    @Override
    public void executeReward(EItem eItem, Player player, RewardAction action, int amount) {
        double price = eItem.buyPrice;
        ItemStack item = new ItemStack(eItem.displayItem.getType(), 1);

        if (action == RewardAction.BUY) {
            if (Transaction.withdraw(player, price * amount)) {
                Transaction.deposit(player, reward * amount);
                MessageSender.buyMessage(player, eItem.name, price * amount, amount);
            } else {
                MessageSender.toPlayer(player, "You do not have enough balance!");
            }
        } else {
            if (player.getInventory().containsAtLeast(item, amount)) {

                for (int i = 0; i < amount; i++) {
                    player.getInventory().removeItem(item);
                }

                Transaction.deposit(player, reward * amount);
                MessageSender.sellMessage(player, eItem.name, String.format("%s$%s", ChatColor.GREEN, reward * amount), amount);
            } else {
                MessageSender.toPlayer(player, "You do not have enough items to sell!");
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
