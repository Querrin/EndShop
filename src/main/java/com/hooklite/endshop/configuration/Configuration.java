package com.hooklite.endshop.configuration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;

public class Configuration {

    public static final String PLUGIN_DIRECTORY = getPluginManager().getPlugin("EndShop").getDataFolder().getPath();
    public static final FileConfiguration DEFAULT_CONFIGURATION = getPluginManager().getPlugin("EndShop").getConfig();
    public static final String SHOPS_DIRECTORY = Paths.get(PLUGIN_DIRECTORY, "shops").toString();

    public static void initDefaultConfigs() {
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();
        InputStream exampleShop = Configuration.class.getResourceAsStream("/example-shop.yml");

        List shops = DEFAULT_CONFIGURATION.getList("shops");
        System.out.println(shops);

        File makeShopDir = new File(SHOPS_DIRECTORY);
        if(!makeShopDir.exists()) makeShopDir.mkdir();

        for(Object shopConfig : shops) {
            System.out.println(shopConfig);
        }
    }

    public static void loadShopConfigs() {

    }
}
