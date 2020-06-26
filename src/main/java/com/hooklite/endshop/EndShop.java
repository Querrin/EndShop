package com.hooklite.endshop;

import co.aikar.commands.BukkitCommandManager;
import com.hooklite.endshop.commands.ShopCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndShop extends JavaPlugin {
    private static Plugin plugin;
    private static BukkitCommandManager commandManager;

    public static Plugin getPlugin() {
        return plugin;
    }

    public static BukkitCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {
        plugin = this;
        commandManager = new BukkitCommandManager(this);

        commandManager.registerCommand(new ShopCommand());
    }

    @Override
    public void onDisable() {

    }
}
