package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.config.Balance;
import com.hooklite.endshop.data.models.EItem;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class EBalanceReward implements EReward {
    private double reward;

    @Override
    public boolean execute(EItem eItem, Player player, int amount) {
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
    public String getType() {
        return "balance";
    }

    @Override
    public EReward getInstance() {
        return new EBalanceReward();
    }

}
