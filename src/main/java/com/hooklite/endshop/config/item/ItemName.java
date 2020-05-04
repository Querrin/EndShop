package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemName implements ItemKey, RequiredKey {
    @Override
    public String getKey() {
        return "name";
    }

    @Override
    public String getKeyPath(String itemSection) {
        return "items." + itemSection + ".name";
    }

    @Override
    public boolean required() {
        return false;
    }

    @Override
    public void setValue(Shop shop, Item item, YamlConfiguration configuration, String itemSection, int ignore) {
        String value = configuration.getString(getKeyPath(itemSection));
    }
}
