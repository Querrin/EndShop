package com.hooklite.endshop.configuration;

import com.hooklite.endshop.logging.MessageLogger;
import com.hooklite.endshop.models.ShopModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getPluginManager;

public class Configuration {

    public static final String PLUGIN_DIRECTORY = getPluginManager().getPlugin("EndShop").getDataFolder().getPath();
    public static final FileConfiguration DEFAULT_CONFIGURATION = getPluginManager().getPlugin("EndShop").getConfig();
    public static final String SHOPS_DIRECTORY = Paths.get(PLUGIN_DIRECTORY, "shops").toString();
    private static final ArrayList<ShopModel> SHOP_CONFIGURATIONS = new ArrayList<>();

    /**
     * Loads and generated all needed configuration files
     */
    public static void loadConfigs() {
        Map<String, Object> exampleShopConfig = getExampleShopConfig();

        List shops = DEFAULT_CONFIGURATION.getList("shops");

        File makeShopDir = new File(SHOPS_DIRECTORY);
        if(!makeShopDir.exists()) {
            if(!makeShopDir.mkdirs()) {
                MessageLogger.toConsole(ChatColor.DARK_RED + "Unable to create shop directories!");
            }
        }

        StringBuilder registeredShops = new StringBuilder();

        for(int i = 0; shops != null && i < shops.size(); i++) {
            try {
                File config = new File(SHOPS_DIRECTORY, shops.get(i).toString() + ".yml");
                if(!config.exists()) {
                    if(!config.createNewFile()) { MessageLogger.toConsole("Unable to create config files!"); }
                }
                else {

                }

                if(shops.size() == 1)
                    registeredShops.append(shops.get(i).toString());
                else
                    registeredShops.append(shops.get(i).toString()).append(", ");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        MessageLogger.toConsole(ChatColor.WHITE + "Loaded shops: " + registeredShops);

        for(int i = 0; shops != null && i < shops.size(); i++) {
            try {
                //Map<String, Object> configuration = new Yaml().load(new FileReader(new File(shops.get(i).toString() + ".yml")));
                YamlConfiguration configuration = new Yaml().load(new FileReader(new File(shops.get(i).toString() + ".yml")));
                ShopModel shopModel = new ShopModel();
                shopModel.setName(shops.get(i).toString());
                shopModel.setTitle(configuration.getString("title"));

                // TODO: Multiple currency support
                String currency = configuration.getString("currency");
                if(currency != null) {
                    if(currency.equalsIgnoreCase("money") || currency.equalsIgnoreCase("emeralds"))
                        shopModel.setCurrency(currency);
                    else
                        shopModel.setCurrency("money");
                }

            }
            catch (FileNotFoundException e) {
                MessageLogger.toConsole(String.format("Could not find \"%s\" file!", shops.get(i).toString() + ".yml"));
                e.printStackTrace();
            }
        }
    }

    /**
     * @return Example shop yaml configuration
     */
    public static Map<String, Object> getExampleShopConfig() {
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();
        return new Yaml().load(Configuration.class.getResourceAsStream("/exampleShop.yml"));
    }
}
