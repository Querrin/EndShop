package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.data.rewards.Reward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface RewardKey extends ConfigKey {
    Reward setValue(Reward reward, Item item, YamlConfiguration configuration, String itemSection, Action action) throws InvalidConfigurationException;

    String getKeyPath(String itemSection, Action action);

    boolean required(YamlConfiguration configuration, String itemSection, Action action);
}
