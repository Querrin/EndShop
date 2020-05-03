package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.EItemKey;
import com.hooklite.endshop.config.interfaces.ERequiredKey;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EShop;
import org.bukkit.configuration.file.YamlConfiguration;

public class EItemSellable implements EItemKey, ERequiredKey {
    @Override
    public void setValue(EShop shop, EItem item, YamlConfiguration configuration, String itemSection, int ignore) {
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
