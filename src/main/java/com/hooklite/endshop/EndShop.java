package com.hooklite.endshop;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndShop extends JavaPlugin {

    @Override
    public void onEnable() {
        FileConfiguration defaultConfig = getConfig();
        saveDefaultConfig();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
