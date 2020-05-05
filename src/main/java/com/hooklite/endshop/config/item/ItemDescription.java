package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ItemDescription implements ItemKey, RequiredKey {
    @Override
    public void setValue(Item item, YamlConfiguration configuration, String itemSection, int ignore) throws InvalidConfigurationException {
        List<String> values = configuration.getStringList(getKeyPath(itemSection));

        if(values.isEmpty() && required())
            throw new InvalidConfigurationException("Description value must be set!");

        List<String> description = new ArrayList<>();
        for(String value : values) {
            description.add(Colors.loadColors(value));
        }

        item.description = description;
    }

    @Override
    public String getKeyPath(String itemSection) {
        return "items." + itemSection + ".description";
    }

    @Override
    public String getKey() {
        return "description";
    }

    @Override
    public boolean required() {
        return false;
    }
}
