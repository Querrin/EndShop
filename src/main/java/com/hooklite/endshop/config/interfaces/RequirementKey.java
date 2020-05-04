package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.requirements.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface RequirementKey extends ConfigKey {
    ERequirement setValue(ERequirement req, Item item, YamlConfiguration config, String itemSection, EAction action) throws InvalidConfigurationException;

    String getKeyPath(String itemSection, EAction action);

    boolean required(YamlConfiguration config, String itemSection, EAction action);
}
