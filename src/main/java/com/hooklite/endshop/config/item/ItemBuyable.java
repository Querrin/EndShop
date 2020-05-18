package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemBuyable implements ItemKey, RequiredKey {
    @Override
    public void setValue(Item item, YamlConfiguration configuration, String itemKey, int ignore) {
        item.buyable = getValue(configuration, itemKey);
    }

    @Override
    public String getKeyPath(String itemKey) {
        return "items." + itemKey + ".buyable";
    }

    @Override
    public String getKey() {
        return "buyable";
    }

    @Override
    public boolean required() {
        return false;
    }

    public boolean getValue(YamlConfiguration configuration, String itemSection) {
        return configuration.getBoolean(getKeyPath(itemSection), true);
    }
}
