package com.hooklite.endshop.data.requirements;

import com.hooklite.endshop.config.Balance;
import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class BalanceRequirement implements Requirement {
    private double requirement;
    private int configAmount;

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
        if(Balance.withdraw(player, requirement * amount * configAmount)) {
            return true;
        }
        else {
            MessageSender.toPlayer(player, "You do not have enough balance!");
            return false;
        }
    }

    @Override
    public boolean undoTransaction(Player player, int amount) {
        return Balance.deposit(player, requirement * amount * configAmount);
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
            throw new InvalidConfigurationException("The requirement is improperly configured!");
        }

    }

    @Override
    public void setAmount(int amount) {
        this.configAmount = amount;
    }

    @Override
    public String getName(int amount) {
        return "$" + requirement * amount * configAmount;
    }

    @Override
    public Requirement getInstance() {
        return new BalanceRequirement();
    }

    @Override
    public int getMaxAmount(Player player) {
        return (int) Math.floor(Configuration.getEcon().getBalance(player) / requirement);
    }
}
