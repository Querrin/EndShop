package com.hooklite.endshop;

import com.hooklite.endshop.commands.EndshopCommand;
import com.hooklite.endshop.commands.ShopCommand;
import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.commands.shop.listeners.InventoryClickListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;

public final class EndShop extends JavaPlugin {

    // TODO: command prefixing

    @Override
    public void onEnable() {
        Configuration.loadConfigs();

        getCommand("endshop").setExecutor(new EndshopCommand());
        getCommand("shop").setExecutor(new ShopCommand());

        getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return super.onTabComplete(sender, command, alias, args);
    }
}
