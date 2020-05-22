package com.hooklite.endshop;

import com.hooklite.endshop.commands.CommandManager;
import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.listeners.TransactionListener;
import com.hooklite.endshop.listeners.InventoryClickListener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;


public final class EndShop extends JavaPlugin {
    @Override
    public void onEnable() {
        getPluginManager().registerEvents(new InventoryClickListener(), this);
        getPluginManager().registerEvents(new TransactionListener(), this);
        Configuration.configurePlugin(this);

        getCommand("endshop").setExecutor(new CommandManager());
        getCommand("shop").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        Configuration.closeShopMenus(this.getServer().getOnlinePlayers());
    }
}
