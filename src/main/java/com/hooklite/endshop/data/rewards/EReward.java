package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.models.EItem;
import org.bukkit.entity.Player;

public interface EReward {
    void executeReward(EItem item, Player player, RewardAction action, int amount);

    void setReward(String reward);

    String getReward();

    String getType();

    EReward getInstance();
}
