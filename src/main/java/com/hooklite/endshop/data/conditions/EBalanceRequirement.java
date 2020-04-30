package com.hooklite.endshop.data.conditions;

import com.hooklite.endshop.data.config.Balance;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class EBalanceRequirement implements ERequirement {
    private double requirement;

    @Override
    public String getType() {
        return "balance";
    }

    @Override
    public boolean check(Player player, int amount) {
        return Balance.hasEnough(player, requirement * amount);
    }

    @Override
    public boolean doTransaction(Player player, int amount) {
        if(Balance.withdraw(player, requirement * amount)) {
            return true;
        }
        else {
            MessageSender.toPlayer(player, "You do not have enough balance!");
            return false;
        }
    }

    @Override
    public boolean undoTransaction(Player player, int amount) {
        return Balance.deposit(player, requirement * amount);
    }

    @Override
    public String getFailedMessage() {
        return "You do not have enough balance!";
    }

    @Override
    public void setRequirement(String requirement) throws InvalidConfigurationException {
        try {
            this.requirement = Double.parseDouble(requirement);
        }
        catch(NumberFormatException e) {
            throw new InvalidConfigurationException("The requirements are improperly configured!");
        }

    }

    @Override
    public String getName(int amount) {
        return ChatColor.GREEN + "$" + requirement * amount;
    }

    @Override
    public ERequirement getInstance() {
        return new EBalanceRequirement();
    }
}
