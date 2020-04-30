package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.data.rewards.EBalanceReward;
import com.hooklite.endshop.data.rewards.ECommandReward;
import com.hooklite.endshop.data.rewards.EItemReward;
import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.logging.MessageSender;
import com.hooklite.endshop.shop.BuySellMenu;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Configuration {
    private static final List<EReward> rewards = new ArrayList<>();
    private static YamlConfiguration defaultConfig;
    private static List<YamlConfiguration> shopConfigs;
    private static List<EShop> shops = new ArrayList<>();
    private static Plugin plugin;
    private static Economy econ;
    private static Permission perms;

    public static YamlConfiguration getDefaultConfig() {
        return defaultConfig;
    }

    public static List<YamlConfiguration> getShopConfigs() {
        return shopConfigs;
    }

    public static List<EShop> getShops() {
        return shops;
    }

    public static List<EReward> getRewards() {
        return rewards;
    }

    /**
     * Registers a new reward type if it isn't already registered.
     *
     * @param reward An instance of EReward.
     */
    public static void addReward(EReward reward) {
        if(!rewards.contains(reward))
            rewards.add(reward);
    }

    public static Permission getPerms() {
        return perms;
    }

    public static Economy getEcon() {
        return econ;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    /**
     * Loads all the required configurations for the plugin to function.
     * <p>
     * THIS METHOD SHOULD ONLY BE CALLED UPON THE START OF THE APPLICATION!
     */
    public static void configurePlugin(Plugin pl) {
        try {
            plugin = pl;

            setDefaultConfig();
            setShopConfigs();

            addReward(new ECommandReward());
            addReward(new EBalanceReward());
            addReward(new EItemReward());

            econ = VaultLoader.getEcon();
            perms = VaultLoader.getPerms();
            shops = ShopLoader.getModels(shopConfigs);

            listRegisteredShops();
        }
        catch(Exception e) {
            MessageSender.toConsole(e.getMessage());
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    public static void closeShopMenus(Collection<? extends Player> players) {
        for(Player player : players) {
            Inventory topInventory = player.getOpenInventory().getTopInventory();

            if(topInventory.getHolder() instanceof ShopMenu || topInventory.getHolder() instanceof ItemMenu || topInventory.getHolder() instanceof BuySellMenu)
                player.closeInventory();
        }
    }

    public static void reloadPlugin() {
        try {
            closeShopMenus(plugin.getServer().getOnlinePlayers());

            reloadDefaultConfig();
            setShopConfigs();

            shops = ShopLoader.getModels(shopConfigs);
            listRegisteredShops();
        }
        catch(Exception e) {
            MessageSender.toConsole(e.getMessage());
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    /**
     * Sets, reads then saves the default plugin configuration file.
     */
    private static void setDefaultConfig() {
        defaultConfig = (YamlConfiguration) plugin.getConfig();
        plugin.saveDefaultConfig();
    }

    private static void reloadDefaultConfig() {
        plugin.reloadConfig();
        defaultConfig = (YamlConfiguration) plugin.getConfig();
    }

    /**
     * Creates the shops directory and loads the configuration files if they don't exist.
     *
     * @throws NullPointerException          If the list element of config.yml is null.
     * @throws IOException                   If the function failed creating the shops directory or the files that come with it.
     * @throws InvalidConfigurationException If the configuration file is improperly configured.
     */
    private static void setShopConfigs() throws IOException, InvalidConfigurationException {
        shopConfigs = new ArrayList<>();

        // Creates the shops directory
        File shopsDirectory = new File(plugin.getDataFolder().getPath(), "shops");
        if(!shopsDirectory.exists()) {
            if(!shopsDirectory.mkdirs())
                throw new IOException("Unable to create shops directory!");
        }

        // Creates a new file for the registered shop if it doesn't exist, then loads the example into it
        for(String shop : defaultConfig.getStringList("shops")) {
            File file = new File(shopsDirectory.getPath(), shop + ".yml");
            if(!file.exists()) {
                if(file.createNewFile())
                    loadExampleConfig(file);
                else
                    throw new IOException("Unable to create shop file!");
            }

            YamlConfiguration configuration = new YamlConfiguration();
            try {
                configuration.load(file);
            }
            catch(InvalidConfigurationException e) {
                throw new InvalidConfigurationException(String.format("File \"%s\" is improperly configured!", file));
            }

            shopConfigs.add(configuration);
        }
    }

    /**
     * Outputs the registered shops to the console.
     */
    private static void listRegisteredShops() {
        List<String> shopList = defaultConfig.getStringList("shops");
        StringBuilder registeredShops = new StringBuilder();

        // Creates a string of registered shops then outputs it into console.
        for(int i = 0; i < shopList.size(); i++) {
            if(shopList.size() == 1)
                registeredShops.append(shopList.get(i));
            else if(i == shopList.size() - 1)
                registeredShops.append(shopList.get(i));
            else
                registeredShops.append(shopList.get(i)).append(", ");
        }

        MessageSender.toConsole("Loaded shops: " + registeredShops.toString());
    }

    /**
     * Loads the example configuration data from a resource stream to a file.
     *
     * @param file The file that the data will be loaded into.
     * @throws IOException If the resource stream data could not be loaded.
     */
    private static void loadExampleConfig(File file) throws IOException {

        // Reads the example config file from the resources folder, then saves it to a file.
        new YamlConfiguration();
        YamlConfiguration exampleConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Configuration.class.getResourceAsStream("/example.yml")));
        exampleConfig.save(file);
    }
}