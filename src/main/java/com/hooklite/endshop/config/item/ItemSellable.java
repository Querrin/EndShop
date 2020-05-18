package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemSellable implements ItemKey, RequiredKey {
    @Override
    public void setValue(Item item, YamlConfiguration configuration, String itemKey, int ignore) {
        item.sellable = getValue(configuration, itemKey);
    }

    @Override
    public String getKeyPath(String itemKey) {
        return "items." + itemKey + ".sellable";
    }

    @Override
    public String getKey() {
        return "sellable";
    }

    @Override
    public boolean required() {
        return false;
    }

    public boolean getValue(YamlConfiguration configuration, String itemSection) {
        return configuration.getBoolean(getKeyPath(itemSection), true);
    }
}
