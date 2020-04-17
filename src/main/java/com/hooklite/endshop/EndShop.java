package com.hooklite.endshop;

import com.hooklite.endshop.commands.CommandManager;
import com.hooklite.endshop.commands.ShopCommand;
import com.hooklite.endshop.configuration.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class EndShop extends JavaPlugin {

    // TODO: command prefixing

    @Override
    public void onEnable() {
        Configuration.loadConfigs();
        getCommand("endshop").setExecutor(new CommandManager());
        getCommand("shop").setExecutor(new ShopCommand());
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
