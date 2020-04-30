package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.models.EItem;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public interface EReward {
    boolean execute(EItem item, Player player, int amount);

    void setReward(String reward) throws InvalidConfigurationException;

    String getReward(int amount);

    String getFailedMessage();

    String getType();

    EReward getInstance();
}
