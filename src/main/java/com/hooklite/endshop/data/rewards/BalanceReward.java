package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.config.Balance;
import com.hooklite.endshop.data.models.Item;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class BalanceReward implements Reward {
    private double reward;
    private int configAmount;

    @Override
    public boolean execute(Item item, Player player, int amount) {
        return Balance.deposit(player, reward * amount);
    }

    @Override
    public String getReward(int amount) {
        return ChatColor.GREEN + "$" + reward * amount;
    }

    @Override
    public String getFailedMessage() {
        return null;
    }

    @Override
    public void setReward(String reward) throws InvalidConfigurationException {
        try {
            this.reward = Double.parseDouble(reward);
        }
        catch(NumberFormatException e) {
            throw new InvalidConfigurationException("Rewards are improperly configured!");
        }
    }

    @Override
    public int getAmount() {
        return configAmount;
    }

    @Override
    public void setAmount(int amount) throws InvalidConfigurationException {
        configAmount = amount;
    }

    @Override
    public String getType() {
        return "balance";
    }

    @Override
    public Reward getInstance() {
        return new BalanceReward();
    }

}
