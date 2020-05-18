package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.requirements.Requirement;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents the item requirement section key.
 */
public interface RequirementKey extends ConfigKey {

    /**
     * Sets the value to the given requirement param.
     *
     * @param req     The object that the value will be set to.
     * @param config  The configuration object that key values will be read from.
     * @param itemKey The item key in configuration file that the value will be read from.
     * @param action  If the requirement is for buying or selling.
     * @return The requirement object with the value set.
     * @throws InvalidConfigurationException If configuration file is improperly configured.
     */
    Requirement setValue(Requirement req, YamlConfiguration config, String itemKey, Action action) throws InvalidConfigurationException;

    /**
     * @return The YamlConfiguration usable path to the key value.
     */
    String getKeyPath(String itemSection, Action action);

    /**
     * @return If the value is required to be set.
     */
    boolean required(YamlConfiguration config, String itemSection, Action action);
}
