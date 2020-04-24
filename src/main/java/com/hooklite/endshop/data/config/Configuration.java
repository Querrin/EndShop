package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.DataModel;
import com.hooklite.endshop.logging.MessageLogger;
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
    private static List<DataModel> shops = new ArrayList<>();

    static {
        try {
            setDefaultConfig();
            setShopConfigs();

            shops = ShopLoader.getModels(shopConfigs);
        } catch (InvalidConfigurationException e) {
            MessageLogger.toConsole(e.getMessage());
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("EndShop")));
        } catch (Exception e) {
            MessageLogger.toConsole("Unable to load configuration files!");
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

    public static List<DataModel> getShops() {
        return shops;
    }

    private static void setDefaultConfig() throws NullPointerException {
        defaultConfig = (YamlConfiguration) Bukkit.getPluginManager().getPlugin("EndShop").getConfig();
    }

    private static void setShopConfigs() throws NullPointerException, IOException, InvalidConfigurationException {
        File shopsDirectory = new File(Bukkit.getPluginManager().getPlugin("EndShop").getDataFolder().getPath(), "shops");
        if (!shopsDirectory.mkdirs())
            MessageLogger.toConsole("Unable to create shops directory!");

        List<String> shopList = (List<String>) defaultConfig.getList("shops");

        for (String shop : shopList) {
            File file = new File(shopsDirectory.getPath() + shop + ".yml");
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

    private static void loadExampleConfig(File file) throws IOException {
        YamlConfiguration exampleConfig = new YamlConfiguration().loadConfiguration(new InputStreamReader(Configuration.class.getResourceAsStream("/example.yml")));
        exampleConfig.save(file);
    }
}
