package com.hooklite.endshop.data.configuration;

import com.hooklite.endshop.data.ConditionConfig;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ConditionResolver {

    protected static List<ConditionConfig> getRewardConfigs(YamlConfiguration config) throws IllegalAccessException {
        Set<String> keys = config.getKeys(false);
        List<ConditionConfig> conditions = new ArrayList<>();


        return conditions;
    }
}
