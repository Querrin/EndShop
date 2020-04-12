package com.hooklite.endshop;

import org.bukkit.Bukkit;

import java.io.File;

import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getServer;

public class Configuration {

    public static final String PLUGIN_DIRECTORY = getPluginManager().getPlugin("EndShop").getDataFolder().getPath();

    public static void initDefaultConfigs() {

    }
}
