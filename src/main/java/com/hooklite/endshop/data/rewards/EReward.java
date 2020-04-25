package com.hooklite.endshop.data.rewards;

import org.bukkit.entity.Player;

public interface EReward {
    void executeReward(Player player);

    RewardAction getRewardAction();

    void setRewardAction(RewardAction action);

    void setReward(String reward);
}
