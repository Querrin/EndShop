package com.hooklite.endshop;

import com.hooklite.endshop.commands.CommandManager;
import com.hooklite.endshop.listeners.InventoryEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;


public final class EndShop extends JavaPlugin {


    @Override
    public void onEnable() {
        getCommand("shop").setExecutor(new CommandManager());
        getCommand("endshop").setExecutor(new CommandManager());

        getPluginManager().registerEvents(new InventoryEventListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
