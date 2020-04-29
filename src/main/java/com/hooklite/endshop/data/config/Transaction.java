package com.hooklite.endshop.data.config;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class Transaction {
    public static boolean withdraw(Player player, double amount) {
        // FIXME: Figure out how to get minimal balance on a server
        Economy econ = Configuration.getEcon();
        if(econ.getBalance(player) >= amount) {
            econ.withdrawPlayer(player, amount);
            return true;
        }

        return false;
    }

    public static boolean deposit(Player player, double amount) {
        Configuration.getEcon().depositPlayer(player, amount);
        return true;
    }
}
