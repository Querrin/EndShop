package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.logging.Colors;
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

        if(value == null || value.isEmpty()) {
            String materialName = item.displayItem.getType().name().replace("_", " ");

            item.name = materialName.substring(0, 1).toUpperCase() + materialName.substring(1);
        }
        else {
            item.name = Colors.loadColors(value);
        }
    }
}
