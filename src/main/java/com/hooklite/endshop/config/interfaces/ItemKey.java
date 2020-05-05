package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface ItemKey extends ConfigKey {
    void setValue(Item item, YamlConfiguration configuration, String itemSection, int counter) throws InvalidConfigurationException;

    String getKeyPath(String itemSection);

    boolean required();
}
