package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.config.Balance;
import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class BalanceReward implements Reward {
    private double reward;
    private int configAmount;

    @Override
    public boolean execute(Item item, Player player, int amount) {
        return Balance.deposit(player, reward * amount * configAmount);
    }

    @Override
    public String getReward(int amount) {
        return String.format("$%.2f", reward * amount * configAmount);
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
    public int getConfigAmount() {
        return configAmount;
    }

    @Override
    public int getMaxAmount(Player player) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void setAmount(int amount) {
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
