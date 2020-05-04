package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemSlot implements ItemKey {
    @Override
    public void setValue(Shop shop, Item item, YamlConfiguration configuration, String itemSection, int counter) throws InvalidConfigurationException {

        // TODO: Max slot number
        String value = configuration.getString("slot");

        if(value == null && required())
            throw new InvalidConfigurationException("Value must be set!");

        try {
            item.slot = value == null ? counter : Integer.parseInt(value);
        }
        catch(NumberFormatException e) {
            throw new InvalidConfigurationException("Invalid value!");
        }
    }

    @Override
    public String getKeyPath(String itemSection) {
        return "items." + itemSection + ".slot";
    }

    @Override
    public String getKey() {
        return "slot";
    }

    @Override
    public boolean required() {
        return false;
    }
}
