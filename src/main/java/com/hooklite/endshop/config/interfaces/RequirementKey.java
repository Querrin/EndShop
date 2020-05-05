package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.requirements.Requirement;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface RequirementKey extends ConfigKey {
    Requirement setValue(Requirement req, Item item, YamlConfiguration config, String itemSection, Action action) throws InvalidConfigurationException;

    String getKeyPath(String itemSection, Action action);

    boolean required(YamlConfiguration config, String itemSection, Action action);
}
