package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.DataModel;
import com.hooklite.endshop.data.models.ECurrency;
import com.hooklite.endshop.data.models.EShop;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

class ShopLoader {

    /**
     * Deserializes and creates a list of shop models from the configuration file.
     *
     * @param shopConfigs A list of configuration files from which objects will be created.
     * @return A list of EShop data models.
     * @throws InvalidConfigurationException If the configuration is improperly configured.
     */
    static List<DataModel> getModels(List<YamlConfiguration> shopConfigs) throws InvalidConfigurationException {
        List<DataModel> shops = new ArrayList<>();
        for (YamlConfiguration config : shopConfigs) {
            EShop shop = new EShop();

            shop.title = config.getString("title");
            shop.description = config.getStringList("description");
            shop.slot = config.getInt("slot");
            shop.currency = ECurrency.VAULT;
            shop.config = config;
            shop.pages = PageLoader.getModels(ItemLoader.getModels(config));
            shop.displayItem = Material.matchMaterial(config.getString("display-item"));

            shops.add(shop);
        }

        return shops;
    }
}
