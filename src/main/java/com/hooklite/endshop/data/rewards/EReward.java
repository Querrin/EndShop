package com.hooklite.endshop.data.rewards;

import org.bukkit.entity.Player;

public interface EReward {
    void executeReward(Player player, RewardAction action, int amount);

    void setReward(String reward);
}
