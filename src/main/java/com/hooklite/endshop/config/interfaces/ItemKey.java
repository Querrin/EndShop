package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents the item configuration key in shop config files.
 */
public interface ItemKey extends ConfigKey {

    /**
     * Sets the value of the given item param.
     *
     * @param item          The object that the value is set to.
     * @param configuration The configuration file.
     * @param itemKey       The key representing the current item.
     * @param counter       Counter.
     * @throws InvalidConfigurationException If configuration is improperly configured.
     */
    void setValue(Item item, YamlConfiguration configuration, String itemKey, int counter) throws InvalidConfigurationException;

    /**
     * Returns the YamlConfiguration path representing the object value
     *
     * @param itemKey The key representing the given item.
     * @return
     */
    String getKeyPath(String itemKey);

    /**
     * @return If the value is required to be set.
     */
    boolean required();
}
