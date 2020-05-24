package com.hooklite.endshop;

import com.hooklite.endshop.commands.CommandManager;
import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.listeners.TransactionListener;
import com.hooklite.endshop.listeners.InventoryClickListener;
import com.hooklite.endshop.logging.MessageSender;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;

public final class EndShop extends JavaPlugin {
    private final int BSTATS_PLUGIN_ID = 7622;

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, BSTATS_PLUGIN_ID);
        MessageSender.toConsole("bStats: " + (metrics.isEnabled() ? "enabled" : "disabled"));

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
