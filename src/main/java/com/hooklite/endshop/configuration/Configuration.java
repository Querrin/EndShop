package com.hooklite.endshop.configuration;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.bukkit.Bukkit.getPluginManager;

public class Configuration {

    public static final String PLUGIN_DIRECTORY = getPluginManager().getPlugin("EndShop").getDataFolder().getPath();
    public static final FileConfiguration DEFAULT_CONFIGURATION = getPluginManager().getPlugin("EndShop").getConfig();
    public static final String SHOPS_DIRECTORY = Paths.get(PLUGIN_DIRECTORY, "shops").toString();

    public static void loadConfigs() {
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();

        // TODO: Get the example-shop.yml file content then write it to new shops
        //ClassLoader classLoader = Configuration.class.getClassLoader();
        //InputStream exampleShop = Configuration.class.getResourceAsStream("example-shop.yml");


        List shops = DEFAULT_CONFIGURATION.getList("shops");

        File makeShopDir = new File(SHOPS_DIRECTORY);
        if(!makeShopDir.exists()) {
            if(!makeShopDir.mkdirs()) {
                MessageLogger.toConsole(ChatColor.DARK_RED + "Unable to create configuration files!");
            }
        }

        String registeredShops = "";

        for(Object shopConfig : shops) {
            try {
                File config = new File(SHOPS_DIRECTORY, shopConfig.toString() + ".yml");
                if(!config.exists()) {
                    config.createNewFile();
                }
                registeredShops += shopConfig.toString() + ", ";
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        MessageLogger.toConsole(ChatColor.WHITE + "Loaded shops: " + registeredShops);
    }
}
