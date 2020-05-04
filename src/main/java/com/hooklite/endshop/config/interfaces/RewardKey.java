package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface RewardKey extends ConfigKey {
    void setValue(EReward reward, YamlConfiguration configuration, String itemSection, EAction action) throws InvalidConfigurationException;

    String getKeyPath(String itemSection, EAction action);
}
