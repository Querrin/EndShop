package com.hooklite.endshop.configuration.keys;

import com.hooklite.endshop.data.EItem;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface ItemKey extends YamlKey {
    void loadValue(YamlConfiguration config, String itemName, EItem item) throws InvalidConfigurationException;

    String getItemKeyLocation(String itemName);

    Object getValue(YamlConfiguration config, String itemName);
}
