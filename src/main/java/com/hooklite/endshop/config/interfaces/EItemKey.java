package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface EItemKey extends EConfigKey {
    void setValue(EShop shop, EItem item, YamlConfiguration configuration, String itemSection, int counter) throws InvalidConfigurationException;

    String getKeyPath(String itemSection);

    boolean required();
}
