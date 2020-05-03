package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.EItemKey;
import com.hooklite.endshop.config.interfaces.ERequiredKey;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class EItemDescription implements EItemKey, ERequiredKey {
    @Override
    public void setValue(EItem item, YamlConfiguration configuration, String itemSection, int ignore) throws InvalidConfigurationException {
        List<String> values = configuration.getStringList(getKeyPath(itemSection));

        if(values.isEmpty() && required())
            throw new InvalidConfigurationException("Value must be set!");

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
