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
    private static Economy econ;
    private static Permission perms;

    public static void configurePlugin() {
        try {
            setDefaultConfig();
            setShopConfigs();

            rewardTypes.add(new EBalanceRewardType());
            rewardTypes.add(new ECommandRewardType());
            rewardTypes.add(new EItemRewardType());

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

    private static void setDefaultConfig() throws NullPointerException {
        defaultConfig = (YamlConfiguration) Bukkit.getPluginManager().getPlugin("EndShop").getConfig();
        Bukkit.getPluginManager().getPlugin("EndShop").saveDefaultConfig();
    }

    private static void setShopConfigs() throws NullPointerException, IOException, InvalidConfigurationException {
        File shopsDirectory = new File(Bukkit.getPluginManager().getPlugin("EndShop").getDataFolder().getPath(), "shops");
        if (!shopsDirectory.exists()) {
            if (!shopsDirectory.mkdirs())
                MessageLogger.toConsole("Unable to create shops directory!");
        }

        List<String> shopList = defaultConfig.getStringList("shops");

        for (String shop : shopList) {
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
                throw new InvalidConfigurationException(String.format("File \"%s\" is improperly configured!"));
            }

            shopConfigs.add(configuration);
        }
    }

    private static void listRegisteredShops() {
        List<String> shopList = defaultConfig.getStringList("shops");
        StringBuilder registeredShops = new StringBuilder();

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

    private static void loadExampleConfig(File file) throws IOException {
        YamlConfiguration exampleConfig = new YamlConfiguration().loadConfiguration(new InputStreamReader(Configuration.class.getResourceAsStream("/example.yml")));
        exampleConfig.save(file);
    }
}
