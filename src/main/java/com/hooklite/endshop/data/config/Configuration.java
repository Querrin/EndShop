package com.hooklite.endshop.data.config;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class Configuration {
    private static FileConfiguration defaultConfig;
    private static List<FileConfiguration> shopConfigurations;

    static {
        try {
            setDefaultConfig();
            setShopConfig();
        } catch (Exception e) {
            MessageLogger.toConsole("Unable to load configuration files!");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("EndShop")));
        }
    }

    private static void setDefaultConfig() {
        defaultConfig = Bukkit.getPluginManager().getPlugin("EndShop").getConfig();
    }

    private static void setShopConfig() throws NullPointerException {
        File shopsFolder = new File(Bukkit.getPluginManager().getPlugin("EndShop").getDataFolder().getPath(), "shops");
        if (!shopsFolder.mkdirs())
            MessageLogger.toConsole("Unable to create shops directory!");


    }
}
