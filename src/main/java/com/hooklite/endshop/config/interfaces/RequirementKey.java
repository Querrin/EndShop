package com.hooklite.endshop.config.interfaces;

import com.hooklite.endshop.data.conditions.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public interface RequirementKey extends ConfigKey {
    void setValue(ERequirement req, YamlConfiguration configuration, String itemSection, EAction action) throws InvalidConfigurationException;

    String getKeyPath(String itemSection, EAction action);

    boolean required(YamlConfiguration configuration, String itemSection, EAction action);
}
