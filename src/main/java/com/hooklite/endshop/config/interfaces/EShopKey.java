package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.EShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface EShopKey extends EConfigKey {
    void setValue(EShop shop, YamlConfiguration configuration, int counter) throws InvalidConfigurationException;

    String getKeyPath();

    boolean required();
}
