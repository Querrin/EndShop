package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.ItemFactory;
import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemSlot implements ItemKey {
    @Override
    public void setValue(Item item, YamlConfiguration configuration, String itemSection, int counter) throws InvalidConfigurationException {
        String value = configuration.getString(getKeyPath(itemSection));

        if(value == null && required())
            throw new InvalidConfigurationException("Slot value must be set!");

        try {
            item.slot = value == null ? ItemFactory.getMaxSlot() + counter + 1 : Integer.parseInt(value);
        }
        catch(NumberFormatException e) {
            throw new InvalidConfigurationException("Invalid shop slotvalue!");
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
