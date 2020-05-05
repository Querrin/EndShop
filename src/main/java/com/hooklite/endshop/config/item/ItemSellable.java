package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemSellable implements ItemKey, RequiredKey {
    @Override
    public void setValue(Item item, YamlConfiguration configuration, String itemSection, int ignore) {
        item.sellable = getValue(configuration, itemSection);
    }

    @Override
    public String getKeyPath(String itemSection) {
        return "items." + itemSection + ".sellable";
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
