package com.hooklite.endshop.data.conditions;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public interface ERequirement {
    String getType();

    boolean check(Player player, int amount);

    boolean doTransaction(Player player, int amount);

    boolean undoTransaction(Player player, int amount);

    String getFailedMessage();

    void setRequirement(String requirement) throws InvalidConfigurationException;

    String getName(int amount);

    ERequirement getInstance();

    int getMaxAmount(Player player);
}
