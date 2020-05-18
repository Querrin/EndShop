package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Shop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents the shop configuration key.
 */
public interface ShopKey extends ConfigKey {

    /**
     * Sets the value from configuration to the given shop param.
     *
     * @param shop          The shop that the value will be set to.
     * @param configuration The configuration file.
     * @param counter       Counter.
     * @throws InvalidConfigurationException If any key is improperly configured.
     */
    void setValue(Shop shop, YamlConfiguration configuration, int counter) throws InvalidConfigurationException;

    /**
     * @return The YamlConfiguration usable path of the key.
     */
    String getKeyPath();

    /**
     * @return If the value is required to be set.
     */
    boolean required();
}
