package com.hooklite.endshop.configuration.keys;

import com.hooklite.endshop.data.EItem;
import com.hooklite.endshop.data.EShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class TitleKey implements ShopKey, ItemKey {
    @Override
    public void loadValue(YamlConfiguration config, EShop shop) throws InvalidConfigurationException {
        String title = (String) getValue(config);

        if(title == null)
            throw new InvalidConfigurationException("Invalid shop title.");

        shop.setTitle(title);
    }

    @Override
    public void loadValue(YamlConfiguration config, String itemName, EItem item) throws InvalidConfigurationException {
        String title = (String) getValue(config, itemName);

        if(title == null)
            throw new InvalidConfigurationException("Invalid item title.");

        item.setTitle(title);
    }

    @Override
    public String getItemKeyLocation(String itemName) {
        return String.format("items.%s.title", itemName);
    }

    @Override
    public String getShopKeyLocation() {
        return "title";
    }

    @Override
    public Object getValue(YamlConfiguration config) {
        return config.getString(getShopKeyLocation());
    }

    @Override
    public Object getValue(YamlConfiguration config, String itemName) {
        return config.getString(getItemKeyLocation(itemName));
    }
}