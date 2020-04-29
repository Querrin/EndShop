package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.models.EItem;
import org.bukkit.entity.Player;

public interface EReward {
    void executeReward(EItem item, Player player, int amount);

    void setReward(String reward);

    void setAction(RewardAction action);

    String getReward();

    String getType();

    EReward getInstance();
}
