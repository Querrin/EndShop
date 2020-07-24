package com.hooklite.endshop.data.configuration;

import com.hooklite.endshop.data.ItemConfig;
import com.hooklite.endshop.data.YamlResolver;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ItemResolver {

    protected static List<ItemConfig> getItems(YamlConfiguration config) throws IllegalAccessException {
        Set<String> keys = config.getKeys(false);
        List<ItemConfig> items = new ArrayList<>();

        for(String key : keys) {
            ItemConfig item = new ItemConfig();

            YamlResolver.resolveValues(config, item, "items." + key);

            item.conditions = ConditionResolver.getRewardConfigs(config);
            item.rewards = RewardResolver.getRewardConfigs(config);

            items.add(item);
        }

        return items;
    }
}
