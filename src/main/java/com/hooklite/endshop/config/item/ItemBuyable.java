package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemBuyable implements ItemKey, RequiredKey {
    @Override
    public void setValue(Shop shop, Item item, YamlConfiguration configuration, String itemSection, int ignore) {
        item.buyable = getValue(configuration, itemSection);
    }

    @Override
    public String getKeyPath(String itemSection) {
        return "items." + itemSection + ".buyable";
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
