package com.hooklite.endshop.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.logging.MessageLogger;
import com.hooklite.endshop.models.ShopItem;
import com.hooklite.endshop.models.Shop;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getServer;

public class Configuration {

    public static final String PLUGIN_DIRECTORY = getPluginManager().getPlugin("EndShop").getDataFolder().getPath();
    public static final FileConfiguration DEFAULT_CONFIGURATION = getPluginManager().getPlugin("EndShop").getConfig();
    public static final String SHOPS_DIRECTORY = Paths.get(PLUGIN_DIRECTORY, "shops").toString();
    private static final ArrayList<Shop> SHOP_CONFIGURATIONS = new ArrayList<>();
    private static Economy ECONOMY = null;
    private static Permission PERMISSIONS = null;
    private static Chat CHAT = null;

    /**
     * Loads and generated all needed configuration files
     */
    public static void loadConfigs() {

        // Vault setup
        if(!setupEconomy()) {
            getServer().getPluginManager().disablePlugin(new EndShop());
        }
        setupPermissions();

        // config.yml generation
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();

        // InputStream files should be low caps, otherwise it doesn't work!
        YamlConfiguration exampleConfiguration = getExampleShopConfig();

        // Creates shop directory
        File makeShopDir = new File(SHOPS_DIRECTORY);
        if(!makeShopDir.exists()) {
            if(!makeShopDir.mkdirs()) {
                MessageLogger.toConsole(ChatColor.DARK_RED + "Unable to create shop directories!");
            }
        }

        // Registers shops and creates their files
        StringBuilder registeredShops = new StringBuilder();
        List shops = DEFAULT_CONFIGURATION.getList("shops");
        for(int i = 0; shops != null && i < shops.size(); i++) {
            try {
                File config = new File(SHOPS_DIRECTORY, shops.get(i).toString() + ".yml");
                if(!config.exists())
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

        // Deserialized config data and inserts the object into SHOP_CONFIGURATIONS
        for(int i = 0; shops != null && i < shops.size(); i++) {
            try {
                YamlConfiguration configuration = new YamlConfiguration().loadConfiguration(new FileReader(new File(SHOPS_DIRECTORY, shops.get(i).toString() + ".yml")));
                Shop shop = new Shop();
                shop.setName(shops.get(i).toString());
                shop.setTitle(configuration.getString("title"));
                int slot = configuration.getInt("slot");
                if(slot >= 0) {
                    shop.setSlot(slot);
                }

                // Load items in shops
                if (configuration.getConfigurationSection("items") != null) {
                    for (String item : configuration.getConfigurationSection("items").getKeys(true)) {
                        if (!item.contains(".")) {
                            ShopItem shopItem = new ShopItem();
                            shopItem.setName(configuration.getString("items." + item + ".name"));
                            // FIXME: Get material from string
                            shopItem.setItem(Material.getMaterial(configuration.getString("items." + item + ".item").toUpperCase()));
                            shopItem.setAmount(configuration.getInt("items." + item + ".amount"));
                            shopItem.setBuyPrice(configuration.getDouble("items." + item + ".buy-price"));
                            shopItem.setSellPrice(configuration.getDouble("items." + item + ".sell-price"));
                            MessageLogger.toConsole(shopItem.getName());
                            MessageLogger.toConsole(shopItem.getItem().toString());
                            MessageLogger.toConsole(Integer.toString(shopItem.getAmount()));
                            MessageLogger.toConsole(Double.toString(shopItem.getBuyPrice()));
                            MessageLogger.toConsole(Double.toString(shopItem.getSellPrice()));
                        }
                    }
                } else {
                    MessageLogger.toConsole(String.format("%sFile \"%s\" is improperly configured!", ChatColor.RED, shops.get(i).toString() + ".yml"));
                }
            }
            catch (FileNotFoundException e) {
                MessageLogger.toConsole(String.format("Could not find \"%s\" file!", shops.get(i).toString() + ".yml"));
                e.printStackTrace();
            }
            catch(NullPointerException e) {
                MessageLogger.toConsole(String.format("%sFile \"%s\" is improperly configured!", ChatColor.RED, shops.get(i).toString() + ".yml"));
                e.printStackTrace();
            }
        }
    }

    /**
     * @return Example shop configuration
     */
    public static YamlConfiguration getExampleShopConfig() {
        return new YamlConfiguration().loadConfiguration(new InputStreamReader(Configuration.class.getResourceAsStream("/exampleshop.yml")));
    }

    private static boolean setupEconomy() {
        if(getServer().getPluginManager().getPlugin("Vault") == null) {
            MessageLogger.toConsole(getServer().getPluginManager().getPlugin("Vault").toString());
            MessageLogger.toConsole("Unable to find Vault! Disabling...");
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null) {
            return false;
        }
        ECONOMY = rsp.getProvider();
        return true;
    }

//    private static boolean setupChat() {
//        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
//        CHAT = rsp.getProvider();
//        MessageLogger.toConsole(CHAT.toString());
//        return true;
//    }

    private static boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        PERMISSIONS = rsp.getProvider();
        return true;
    }

}
