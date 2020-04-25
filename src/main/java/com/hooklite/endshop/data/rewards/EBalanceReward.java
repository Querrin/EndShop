package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.config.Configuration;
import org.bukkit.entity.Player;

public class EBalanceReward implements EReward {
    private double reward;

    @Override
    public void executeReward(Player player, RewardAction action, int amount) {
        if (action == RewardAction.BUY) {
            Configuration.getEcon().withdrawPlayer(player, reward * amount);
        } else {
            Configuration.getEcon().depositPlayer(player, reward * amount);
        }
    }

    @Override
    public void setReward(String reward) {
        this.reward = Double.parseDouble(reward);
    }
}
