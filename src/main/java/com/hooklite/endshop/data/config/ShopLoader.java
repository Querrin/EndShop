package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ShopLoader {

    /**
     * Deserializes and creates a list of shop models from the configuration file.
     *
     * @param shopConfigs A list of configuration files from which objects will be created.
     * @return A list of EShop data models.
     * @throws InvalidConfigurationException If the configuration is improperly configured.
     */
    static List<EShop> getModels(List<YamlConfiguration> shopConfigs) throws InvalidConfigurationException {
        List<EShop> shops = new ArrayList<>();
        for (YamlConfiguration config : shopConfigs) {
            EShop shop = new EShop();

            List<String> description = config.getStringList("description");
            for (String line : description) {
                line = Colors.loadColors(line);
            }

            if (Material.matchMaterial(config.getString("display-item")) == null) {
                throw new InvalidConfigurationException(String.format("display-item of \"%s\" is improperly configured!", config.getName()));
            }

            shop.title = Colors.loadColors(Colors.loadColors(config.getString("title")));
            shop.description = description;
            shop.slot = config.getInt("slot");
            shop.config = config;
            shop.pages = PageLoader.getModels(ItemLoader.getModels(config));
            shop.displayItem = Material.matchMaterial(Objects.requireNonNull(config.getString("display-name")));

            shops.add(shop);
        }

        return shops;
    }
}
