package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.ECurrency;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.data.rewards.ItemReward;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    private static final List<EShop> shops = new ArrayList<>();

    static {
        try {
            setDefaultConfig();
            setShopConfigs();
            setShopModels();
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

    public static List<EShop> getShops() {
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

    private static void setShopModels() throws InvalidConfigurationException {
        for (YamlConfiguration config : shopConfigs) {
            EShop shop = new EShop();

            shop.title = config.getString("title");
            shop.description = config.getStringList("description");
            shop.slot = config.getInt("slot");
            shop.currency = ECurrency.VAULT;
            shop.config = config;
            shop.pages = getPages(getItems(config));
            shop.displayItem = Material.matchMaterial(config.getString("display-item"));

            shops.add(shop);
        }
    }

    private static List<EPage> getPages(List<EItem> items) {
        ArrayList<EPage> pages = new ArrayList<>();
        int pageAmount = (int) Math.ceil(items.size() / 45.0);

        int j = 0;
        for (int i = 0; i < pageAmount; i++) {
            EPage page = new EPage();
            page.setNumber(i);

            ArrayList<EItem> eItems = new ArrayList<>();
            while (j < items.size()) {
                eItems.add(items.get(j));

                if (j % 44 == 0) {
                    j++;
                    break;
                }
                j++;
            }

            page.setItems(eItems);
            pages.add(page);
        }

        return pages;
    }

    private static List<EItem> getItems(YamlConfiguration config) throws InvalidConfigurationException {
        if (config.getConfigurationSection("items") != null) {
            ArrayList<EItem> items = new ArrayList<>();

            for (String item : config.getConfigurationSection("items").getKeys(true)) {
                if (!item.contains(".")) {
                    EItem eItem = new EItem();

                    eItem.name = config.getString("name");
                    eItem.description = config.getStringList("description").isEmpty() ? config.getStringList("description") : new ArrayList<>();
                    eItem.displayItem = Material.matchMaterial(config.getString("display-item"));
                    eItem.buyPrice = config.getDouble("buy-price");
                    eItem.sellPrice = config.getDouble("sell-price");

                    // TODO: Get a valid reward from the configuration file
                    eItem.reward = new ItemReward();
                }
            }
            return items;
        } else {
            throw new InvalidConfigurationException("Items are not properly configured!");
        }
    }
}
