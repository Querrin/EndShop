package com.hooklite.endshop.data.configuration;

import com.hooklite.endshop.data.RewardConfig;
import com.hooklite.endshop.data.YamlResolver;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class RewardResolver {

    protected static List<RewardConfig> getRewardConfigs(YamlConfiguration config) throws IllegalAccessException {
        Set<String> keys = config.getKeys(false);
        List<RewardConfig> rewards = new ArrayList<>();

        for(String key : keys) {
            RewardConfig reward = new RewardConfig();

            rewards.add(YamlResolver.resolveValues(config, reward, "items." + key));
        }

        return rewards;
    }
}
