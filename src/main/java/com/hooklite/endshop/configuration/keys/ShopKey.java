package com.hooklite.endshop.configuration.keys;

import com.hooklite.endshop.data.EShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface ShopKey extends YamlKey {
    void loadValue(YamlConfiguration config, EShop shop) throws InvalidConfigurationException;

    String getShopKeyLocation();

    Object getValue(YamlConfiguration config);
}
