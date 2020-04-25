package com.hooklite.endshop;

import com.hooklite.endshop.commands.CommandManager;
import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.listeners.InventoryEventListener;
import com.hooklite.endshop.shop.listeners.ShopMenuOpenListener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;


public final class EndShop extends JavaPlugin {


    @Override
    public void onEnable() {
        Configuration.configurePlugin();

        getCommand("shop").setExecutor(new CommandManager());
        getCommand("endshop").setExecutor(new CommandManager());

        getPluginManager().registerEvents(new InventoryEventListener(), this);
        getPluginManager().registerEvents(new ShopMenuOpenListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
