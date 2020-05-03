package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.EItemKey;
import com.hooklite.endshop.data.models.EItem;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class EItemSlot implements EItemKey {
    @Override
    public void setValue(EItem item, YamlConfiguration configuration, String itemSection, int counter) throws InvalidConfigurationException {

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
