package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class ItemName implements ItemKey, RequiredKey {
    @Override
    public String getKey() {
        return "name";
    }

    @Override
    public String getKeyPath(String itemKey) {
        return "items." + itemKey + ".name";
    }

    @Override
    public boolean required() {
        return false;
    }

    @Override
    public void setValue(Item item, YamlConfiguration configuration, String itemKey, int ignore) {
        String value = configuration.getString(getKeyPath(itemKey));

        if(value == null || value.isEmpty()) {
            String materialName = item.displayItem.getType().name().replace("_", " ").toLowerCase();

            item.name = materialName.substring(0, 1).toUpperCase() + materialName.substring(1);
        }
        else {
            item.name = Colors.loadColors(value);
        }
    }

    public String getValue(ItemStack item, YamlConfiguration configuration, String itemSection) {
        String value = configuration.getString(getKeyPath(itemSection));

        if(value == null || value.isEmpty()) {
            String materialName = item.getType().name().replace("_", " ").toLowerCase();

            return materialName.substring(0, 1).toUpperCase() + materialName.substring(1);
        }
        else {
            return Colors.loadColors(value);
        }
    }
}
