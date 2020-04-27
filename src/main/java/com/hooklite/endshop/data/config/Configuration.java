package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.data.rewards.types.EBalanceRewardType;
import com.hooklite.endshop.data.rewards.types.ECommandRewardType;
import com.hooklite.endshop.data.rewards.types.EItemRewardType;
import com.hooklite.endshop.data.rewards.types.ERewardType;
import com.hooklite.endshop.logging.MessageLogger;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Configuration {
    private static YamlConfiguration defaultConfig;
    private static final List<YamlConfiguration> shopConfigs = new ArrayList<>();
    private static List<EShop> shops = new ArrayList<>();
    private static final List<ERewardType> rewardTypes = new ArrayList<>();
    private static Plugin plugin;
    private static Economy econ;
    private static Permission perms;

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

            addRewardType(new EBalanceRewardType());
            addRewardType(new ECommandRewardType());
            addRewardType(new EItemRewardType());

            econ = VaultLoader.getEcon();
            perms = VaultLoader.getPerms();
            shops = ShopLoader.getModels(shopConfigs);

            listRegisteredShops();
        } catch (Exception e) {
            MessageLogger.toConsole(e.getMessage());
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("EndShop")));
        }
    }

    public static YamlConfiguration getDefaultConfig() {
        return defaultConfig;
    }

    public static List<YamlConfiguration> getShopConfigs() {
        return shopConfigs;
    }

    public static List<EShop> getShops() {
        return shops;
    }

    public static List<ERewardType> getRewardTypes() {
        return rewardTypes;
    }

    public static void addReward(ERewardType type) {
        rewardTypes.add(type);
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
     * Registers a new reward type if it isn't already registered.
     *
     * @param type An ERewardType object.
     */
    public static void addRewardType(ERewardType type) {
        if (!rewardTypes.contains(type))
            rewardTypes.add(type);
    }

    /**
     * Sets, reads then saves the default plugin configuration file.
     */
    private static void setDefaultConfig() {
        defaultConfig = (YamlConfiguration) plugin.getConfig();
        plugin.saveDefaultConfig();
    }

    /**
     * Creates the shops directory and loads the configuration files if they don't exist.
     *
     * @throws NullPointerException          If the list element of config.yml is null.
     * @throws IOException                   If the function failed creating the shops directory or the files that come with it.
     * @throws InvalidConfigurationException If the configuration file is improperly configured.
     */
    private static void setShopConfigs() throws IOException, InvalidConfigurationException {

        // Creates the shops directory
        File shopsDirectory = new File(plugin.getDataFolder().getPath(), "shops");
        if (!shopsDirectory.exists()) {
            if (!shopsDirectory.mkdirs())
                throw new IOException("Unable to create shops directory!");
        }

        // Creates a new file for the registered shop if it doesn't exist, then loads the example into it
        for (String shop : defaultConfig.getStringList("shops")) {
            File file = new File(shopsDirectory.getPath(), shop + ".yml");
            if (!file.exists()) {
                if (file.createNewFile())
                    loadExampleConfig(file);
                else
                    throw new IOException("Unable to create shop file!");
            }

            YamlConfiguration configuration = new YamlConfiguration();
            try {
                configuration.load(file);
            } catch (InvalidConfigurationException e) {
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
        for (int i = 0; i < shopList.size(); i++) {
            if (shopList.size() == 1)
                registeredShops.append(shopList.get(i));
            else if (i == shopList.size() - 1)
                registeredShops.append(shopList.get(i));
            else
                registeredShops.append(shopList.get(i)).append(", ");
        }

        MessageLogger.toConsole("Loaded shops: " + registeredShops.toString());
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