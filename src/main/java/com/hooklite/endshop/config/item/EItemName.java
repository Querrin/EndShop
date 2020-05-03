package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.EItemKey;
import com.hooklite.endshop.config.interfaces.ERequiredKey;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EShop;
import org.bukkit.configuration.file.YamlConfiguration;

public class EItemName implements EItemKey, ERequiredKey {
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
    public void setValue(EShop shop, EItem item, YamlConfiguration configuration, String itemSection, int ignore) {
        String value = configuration.getString(getKeyPath(itemSection));
    }
}
