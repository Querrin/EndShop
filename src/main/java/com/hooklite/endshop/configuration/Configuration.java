package com.hooklite.endshop.configuration;

import com.hooklite.endshop.logging.MessageLogger;
import com.hooklite.endshop.models.Shop;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
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
    private static final ArrayList<Shop> SHOP_CONFIGURATIONS = new ArrayList<>();

    /**
     * Loads and generated all needed configuration files
     */
    public static void loadConfigs() {
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();

        // InputStream files should be low caps, otherwise it doesn't work!
        YamlConfiguration exampleConfiguration = new YamlConfiguration().loadConfiguration(new InputStreamReader(Configuration.class.getResourceAsStream("/exampleshop.yml")));
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
                exampleConfiguration.save(config);

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
                YamlConfiguration configuration = new YamlConfiguration().loadConfiguration(new FileReader(new File(SHOPS_DIRECTORY, shops.get(i).toString() + ".yml")));
                Shop shop = new Shop();
                shop.setName(shops.get(i).toString());
                shop.setTitle(configuration.getString("title"));

                // TODO: Multiple currency support
                String currency = configuration.getString("currency");
                if(currency != null) {
                    if(currency.equalsIgnoreCase("money") || currency.equalsIgnoreCase("emeralds"))
                        shop.setCurrency(currency);
                    else
                        shop.setCurrency("money");
                }
                int slot = configuration.getInt("slot");
                if(slot >= 0) {
                    shop.setSlot(slot);
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
//    public static Map<String, Object> getExampleShopConfig() {
//        InputStream stream = Configuration.class.getResourceAsStream("/exampleshop.yml");
//        Map<String, Object> yaml = new Yaml().load(stream);
//        try {
//            stream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        MessageLogger.toConsole(yaml.toString());
//        return yaml;
//    }
}
