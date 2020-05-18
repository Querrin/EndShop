package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.data.rewards.Reward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents the item reward section key.
 */
public interface RewardKey extends ConfigKey {

    /**
     * Sets the value of the given reward param.
     *
     * @param reward        The object that the value will be set to.
     * @param configuration The configuration file.
     * @param itemKey       The key representing the item section.
     * @param action        If the reward is for buying or selling.
     * @return The reward with the value set.
     * @throws InvalidConfigurationException If any configuration key is improperly configured.
     */
    Reward setValue(Reward reward, YamlConfiguration configuration, String itemKey, Action action) throws InvalidConfigurationException;

    /**
     * @param itemKey The key of the item section.
     * @param action  If the path is for buying or selling.
     * @return The YamlConfiguration usable path to the key value.
     */
    String getKeyPath(String itemKey, Action action);

    /**
     * @return If the value is required to be set.
     */
    boolean required(YamlConfiguration configuration, String itemSection, Action action);
}
