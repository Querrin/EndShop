package com.hooklite.endshop.data.config;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

class VaultLoader {

    public static Economy getEcon() throws Exception {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            throw new Exception("Vault could not be found!");
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            throw new Exception("No economy service provider found!");
        }
        return rsp.getProvider();
    }

    public static Permission getPerms() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        return rsp.getProvider();
    }
}