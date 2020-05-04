package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface RewardKey extends ConfigKey {
    EReward setValue(EReward reward, Item item, YamlConfiguration configuration, String itemSection, EAction action) throws InvalidConfigurationException;

    String getKeyPath(String itemSection, EAction action);

    boolean required(YamlConfiguration configuration, String itemSection, EAction action);
}
