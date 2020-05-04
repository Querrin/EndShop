package com.hooklite.endshop.config;

import com.hooklite.endshop.config.interfaces.ConfigKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.config.interfaces.ShopKey;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

class ShopFactory {
    static List<Shop> getShops(List<YamlConfiguration> configs) throws InvalidConfigurationException {
        List<Shop> shops = new ArrayList<>();

        Shop shop = new Shop();
        for(int i = 0; i < configs.size(); i++) {
            for(RequiredKey rKey : Configuration.getRequiredKeys()) {
                if(rKey instanceof ShopKey) {
                    ((ShopKey) rKey).setValue(shop, configs.get(i), i);
                }
            }

            for(ConfigKey cKey : Configuration.getConfigKeys()) {
                if(cKey instanceof ShopKey) {
                    ((ShopKey) cKey).setValue(shop, configs.get(i), i);
                }
            }

            shop.pages = PageFactory.getPages(shop, ItemFactory.getItems(configs.get(i), shop));
            shops.add(shop);
        }


        return shops;
    }
}
