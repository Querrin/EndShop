package com.hooklite.endshop.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;

public class Configuration {

    public static final String PLUGIN_DIRECTORY = getPluginManager().getPlugin("EndShop").getDataFolder().getPath();
    public static final FileConfiguration DEFAULT_CONFIGURATION = getPluginManager().getPlugin("EndShop").getConfig();
    public static final String SHOPS_DIRECTORY = Paths.get(PLUGIN_DIRECTORY, "shops").toString();

    public static void loadConfigs() {
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();
        // InputStream exampleShop = Configuration.class.getResourceAsStream("/example-shop.yml");

        List shops = DEFAULT_CONFIGURATION.getList("shops");

        File makeShopDir = new File(SHOPS_DIRECTORY);
        if(!makeShopDir.exists()) makeShopDir.mkdir();

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
        System.out.println(ChatColor.GREEN + "Shops: " + registeredShops);
    }
}
