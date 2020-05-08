package com.hooklite.endshop.data.requirements;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public interface Requirement {
    String getType();

    boolean check(Player player, int amount);

    boolean doTransaction(Player player, int amount);

    boolean undoTransaction(Player player, int amount);

    String getFailedMessage();

    void setRequirement(String requirement) throws InvalidConfigurationException;

    void setAmount(int amount);

    String getName(int amount);

    Requirement getInstance();

    int getMaxAmount(Item item, Player player, Action action);
}
