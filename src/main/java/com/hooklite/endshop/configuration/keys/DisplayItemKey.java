package com.hooklite.endshop.configuration.keys;

import com.hooklite.endshop.data.EItem;
import com.hooklite.endshop.data.EShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;


public class DisplayItemKey implements ShopKey, ItemKey {
    @Override
    public void loadValue(YamlConfiguration config, String itemName, EItem item) {
        item.setDisplayItem((String) getValue(config, itemName));
    }

    @Override
    public String getItemKeyLocation(String itemName) {
        return String.format("items.%s.display-item", itemName);
    }

    @Override
    public Object getValue(YamlConfiguration config, String itemName) {
        return config.getString(getItemKeyLocation(itemName));
    }

    @Override
    public void loadValue(YamlConfiguration config, EShop shop) throws InvalidConfigurationException {
        shop.setDisplayItem((String) getValue(config));
    }

    @Override
    public String getShopKeyLocation() {
        return "display-item";
    }

    @Override
    public Object getValue(YamlConfiguration config) {
        return config.getString(getShopKeyLocation());
    }
}
