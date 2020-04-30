package com.hooklite.endshop.data.config;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class Balance {
    private static final Economy ECON = Configuration.getEcon();

    public static boolean hasEnough(Player player, double amount) {
        // FIXME: Figure out how to get minimal balance on a server
        return ECON.getBalance(player) >= amount;
    }

    public static boolean withdraw(Player player, double amount) {
        if(hasEnough(player, amount)) {
            ECON.withdrawPlayer(player, amount);
            return true;
        }

        return false;
    }

    public static boolean deposit(Player player, double amount) {
        Configuration.getEcon().depositPlayer(player, amount);
        return true;
    }
}
