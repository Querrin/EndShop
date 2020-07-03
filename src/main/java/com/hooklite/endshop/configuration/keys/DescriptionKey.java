package com.hooklite.endshop.configuration.keys;

import com.hooklite.endshop.data.EItem;
import com.hooklite.endshop.data.EShop;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class DescriptionKey implements ShopKey, ItemKey {
    @Override
    public void loadValue(YamlConfiguration config, String itemName, EItem item) {
        item.setDescription((List<String>) getValue(config, itemName));
    }

    @Override
    public String getItemKeyLocation(String itemName) {
        return String.format("items.%s.description", itemName);
    }

    @Override
    public Object getValue(YamlConfiguration config, String itemName) {
        return config.getStringList(getItemKeyLocation(itemName));
    }

    @Override
    public void loadValue(YamlConfiguration config, EShop shop) {
        shop.setDescription((List<String>) getValue(config));
    }

    @Override
    public String getShopKeyLocation() {
        return "description";
    }

    @Override
    public Object getValue(YamlConfiguration config) {
        return config.getStringList(getShopKeyLocation());
    }
}
