package com.hooklite.endshop;

import com.hooklite.endshop.commands.CommandManager;
import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.listeners.inventory.InventoryClickListener;
import com.hooklite.endshop.listeners.navigation.*;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;


public final class EndShop extends JavaPlugin {
    @Override
    public void onEnable() {
        Configuration.configurePlugin(this);

        getCommand("shop").setExecutor(new CommandManager());
        getCommand("endshop").setExecutor(new CommandManager());

        getPluginManager().registerEvents(new InventoryClickListener(), this);

        getPluginManager().registerEvents(new ShopMenuOpenListener(), this);
        getPluginManager().registerEvents(new ItemMenuOpenListener(), this);
        getPluginManager().registerEvents(new BuySellMenuOpenListener(), this);

        getPluginManager().registerEvents(new BackListener(), this);
        getPluginManager().registerEvents(new PageNavigationListener(), this);
        getPluginManager().registerEvents(new PreviousPageListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
