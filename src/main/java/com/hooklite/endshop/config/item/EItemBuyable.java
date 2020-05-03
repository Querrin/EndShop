package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.EItemKey;
import com.hooklite.endshop.config.interfaces.ERequiredKey;
import com.hooklite.endshop.data.models.EItem;
import org.bukkit.configuration.file.YamlConfiguration;

public class EItemBuyable implements EItemKey, ERequiredKey {
    @Override
    public void setValue(EItem item, YamlConfiguration configuration, String itemSection, int ignore) {
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
