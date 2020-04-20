package com.hooklite.endshop.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.logging.MessageLogger;
import com.hooklite.endshop.commands.shop.gui.ShopsGui;
import com.hooklite.endshop.commands.shop.ShopItem;
import com.hooklite.endshop.commands.shop.Shop;
import com.hooklite.endshop.commands.shop.gui.ShopGui;
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
    private static final String PLUGIN_DIRECTORY = getPluginManager().getPlugin("EndShop").getDataFolder().getPath();
    private static final String SHOPS_DIRECTORY = Paths.get(PLUGIN_DIRECTORY, "shops").toString();
    private static FileConfiguration DEFAULT_CONFIGURATION = getPluginManager().getPlugin("EndShop").getConfig();

    private static ArrayList<Shop> SHOPS = new ArrayList<>();
    private static Economy ECONOMY = null;
    private static Permission PERMISSIONS = null;

    public static List<Shop> getShops() { return SHOPS; }

    /**
     * Loads and creates all needed configuration files
     * SHOULD ONLY BE USED ONCE ON STARTUP!
     */
    public static void loadConfigs() {

        // Vault setup
        if (!setupEconomy()) {
            getServer().getPluginManager().disablePlugin(new EndShop());
        }
        setupPermissions();

        // config.yml generation
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();

        // InputStream files should be low caps, otherwise it doesn't work!
        YamlConfiguration exampleConfiguration = getExampleShopConfig();

        // Creates shop directory
        File makeShopDir = new File(SHOPS_DIRECTORY);
        if (!makeShopDir.exists()) {
            if (!makeShopDir.mkdirs()) {
                MessageLogger.toConsole(ChatColor.DARK_RED + "Unable to create shop directories!");
            }
        }
        registerShops();
    }

    /**
     * Reloads the configuration file data.
     */
    public static void reloadConfigs() {
        getPluginManager().getPlugin("EndShop").reloadConfig();

        // Resets current variable values
        DEFAULT_CONFIGURATION = getPluginManager().getPlugin("EndShop").getConfig();
        SHOPS = new ArrayList<>();

        // config.yml generation
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();

        registerShops();
    }

    /**
     * Registers all shops from the configuration files.
     */
    private static void registerShops() {
        // InputStream files should be low caps, otherwise it doesn't work!
        YamlConfiguration exampleConfiguration = getExampleShopConfig();

        // Registers shops and creates their files
        // FIXME: Potential name clash in config.yml
        StringBuilder registeredShops = new StringBuilder();
        List shops = DEFAULT_CONFIGURATION.getList("shops");
        for (int i = 0; shops != null && i < shops.size(); i++) {
            try {
                File config = new File(SHOPS_DIRECTORY, shops.get(i).toString() + ".yml");
                if (!config.exists())
                    exampleConfiguration.save(config);

                if (shops.size() == 1)
                    registeredShops.append(shops.get(i).toString());
                else if (i == shops.size() - 1)
                    registeredShops.append(shops.get(i).toString());
                else
                    registeredShops.append(shops.get(i).toString()).append(", ");
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        MessageLogger.toConsole(ChatColor.WHITE + "Loaded shops: " + registeredShops);

        // Deserialized config data and inserts the object into SHOP_CONFIGURATIONS
        for (int i = 0; shops != null && i < shops.size(); i++) {
            try {
                // Create a shop and set its required values
                YamlConfiguration configuration = new YamlConfiguration().loadConfiguration(new FileReader(new File(SHOPS_DIRECTORY, shops.get(i).toString() + ".yml")));
                Shop shop = new Shop();
                shop.setName(shops.get(i).toString());
                shop.setTitle(configuration.getString("title"));
                shop.setDisplayItem(Material.matchMaterial(configuration.getString("display-item")));
                int slot = configuration.getInt("slot");
                if (slot >= 0) {
                    shop.setSlot(slot);
                }

                // Load items into the shop
                if (configuration.getConfigurationSection("items") != null) {
                    for (String item : configuration.getConfigurationSection("items").getKeys(true)) {
                        if (!item.contains(".")) {
                            ShopItem shopItem = new ShopItem();
                            shopItem.setName(configuration.getString("items." + item + ".name"));
                            shopItem.setItem(Material.matchMaterial(configuration.getString("items." + item + ".item")));
                            shopItem.setBuyPrice(configuration.getDouble("items." + item + ".buy-price"));
                            shopItem.setSellPrice(configuration.getDouble("items." + item + ".sell-price"));
                            shop.addShopItem(shopItem);
                        }
                    }
                } else {
                    MessageLogger.toConsole(String.format("%sFile \"%s\" is improperly configured!", ChatColor.RED, shops.get(i).toString() + ".yml"));
                }
                SHOPS.add(shop);
                ShopsGui.initInventories(SHOPS);
            } catch (FileNotFoundException e) {
                MessageLogger.toConsole(String.format("Could not find \"%s\" file!", shops.get(i).toString() + ".yml"));
                e.printStackTrace();
            } catch (NullPointerException e) {
                MessageLogger.toConsole(String.format("%sFile \"%s\" is improperly configured!", ChatColor.RED, shops.get(i).toString() + ".yml"));
                e.printStackTrace();
            }
        }
        ShopsGui.initInventories(SHOPS);
        ShopGui.initShopItemInventories(SHOPS);
    }


    /**
     * @return Example shop configuration
     */
    public static YamlConfiguration getExampleShopConfig() {
        return new YamlConfiguration().loadConfiguration(new InputStreamReader(Configuration.class.getResourceAsStream("/exampleshop.yml")));
    }

    private static boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            MessageLogger.toConsole(getServer().getPluginManager().getPlugin("Vault").toString());
            MessageLogger.toConsole("Unable to find Vault! Disabling...");
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        ECONOMY = rsp.getProvider();
        return true;
    }

    private static boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        PERMISSIONS = rsp.getProvider();
        return true;
    }

}
