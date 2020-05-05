package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public interface Reward {
    boolean execute(Item item, Player player, int amount);

    void setReward(String reward) throws InvalidConfigurationException;

    int getAmount();

    void setAmount(int amount) throws InvalidConfigurationException;

    String getReward(int amount);

    String getFailedMessage();

    String getType();

    Reward getInstance();
}
