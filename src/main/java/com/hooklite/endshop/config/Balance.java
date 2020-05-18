package com.hooklite.endshop.config;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class Balance {
    private static final Economy ECON = Configuration.getEcon();

    /**
     * @param player The player to check.
     * @param amount The amount that the player might have
     * @return If the player has enough balance.
     */
    public static boolean hasEnough(Player player, double amount) {
        // FIXME: Figure out how to get minimal balance on a server
        return ECON.getBalance(player) >= amount;
    }

    /**
     * Withdraws the given amount from the player's balance
     *
     * @return If the transaction was successful.
     */
    public static boolean withdraw(Player player, double amount) {
        if(hasEnough(player, amount)) {
            ECON.withdrawPlayer(player, amount);
            return true;
        }

        return false;
    }

    /**
     * Deposits the amount into the player's balance.
     *
     * @return If the transaction was successful.
     */
    public static boolean deposit(Player player, double amount) {
        Configuration.getEcon().depositPlayer(player, amount);
        return true;
    }
}
