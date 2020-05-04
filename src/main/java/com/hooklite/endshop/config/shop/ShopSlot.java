package com.hooklite.endshop.config.shop;

import com.hooklite.endshop.config.interfaces.ShopKey;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ShopSlot implements ShopKey {
    @Override
    public String getKey() {
        return "slot";
    }

    @Override
    public String getKeyPath() {
        return "slot";
    }

    @Override
    public boolean required() {
        return false;
    }

    @Override
    public void setValue(Shop shop, YamlConfiguration configuration, int counter) throws InvalidConfigurationException {

        // TODO: Max slot number
        String value = configuration.getString("slot");

        if(value == null && required())
            throw new InvalidConfigurationException("Value must be set!");

        try {
            shop.slot = value == null ? counter : Integer.parseInt(value);
        }
        catch(NumberFormatException e) {
            throw new InvalidConfigurationException("Invalid value!");
        }
    }
}
