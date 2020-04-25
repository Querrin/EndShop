package com.hooklite.endshop.data.rewards;

import org.bukkit.entity.Player;

public class ECommandReward implements EReward {
    private String reward;

    @Override
    public void executeReward(Player player, RewardAction action, int amount) {
        // TODO: Set the reward action
    }

    @Override
    public void setReward(String reward) {
        this.reward = reward;
    }
}
