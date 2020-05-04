package com.hooklite.endshop.config.shop;

import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.config.interfaces.ShopKey;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ShopTitle implements ShopKey, RequiredKey {

    @Override
    public String getKey() {
        return "title";
    }

    @Override
    public String getKeyPath() {
        return "title";
    }

    @Override
    public boolean required() {
        return true;
    }

    @Override
    public void setValue(Shop shop, YamlConfiguration configuration, int ignore) throws InvalidConfigurationException {
        String value = configuration.getString("title");

        if(value != null) {
            shop.title = Colors.loadColors(value);
        }
        else {
            if(required())
                throw new InvalidConfigurationException("Value must be set!");
        }
    }
}
