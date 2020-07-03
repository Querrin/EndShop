package com.hooklite.endshop.configuration.keys;

import com.hooklite.endshop.data.EItem;
import org.bukkit.configuration.file.YamlConfiguration;

public class SellKey implements ItemKey {
    @Override
    public void loadValue(YamlConfiguration config, String itemName, EItem item) {
        item.setSell((boolean) getValue(config, itemName));
    }

    @Override
    public String getItemKeyLocation(String itemName) {
        return String.format("items.%s.sell", itemName);
    }

    @Override
    public Object getValue(YamlConfiguration config, String itemName) {
        return config.getBoolean(getItemKeyLocation(itemName));
    }
}
