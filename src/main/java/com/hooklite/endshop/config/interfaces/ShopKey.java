package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Shop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface ShopKey extends ConfigKey {
    void setValue(Shop shop, YamlConfiguration configuration, int counter) throws InvalidConfigurationException;

    String getKeyPath();

    boolean required();
}
