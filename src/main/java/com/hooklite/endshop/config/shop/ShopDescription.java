package com.hooklite.endshop.config.shop;

import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.config.interfaces.ShopKey;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ShopDescription implements ShopKey, RequiredKey {
    @Override
    public String getKey() {
        return "description";
    }

    @Override
    public String getKeyPath() {
        return "description";
    }

    @Override
    public boolean required() {
        return false;
    }

    @Override
    public void setValue(Shop shop, YamlConfiguration configuration, int ignore) throws InvalidConfigurationException {
        List<String> values = configuration.getStringList("description");

        if(values.isEmpty() && required())
            throw new InvalidConfigurationException("Value must be set!");

        List<String> description = new ArrayList<>();
        for(String value : values) {
            description.add(Colors.loadColors(value));
        }

        shop.description = description;
    }
}
