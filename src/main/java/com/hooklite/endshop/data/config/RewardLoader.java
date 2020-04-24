package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.data.rewards.types.ERewardType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class RewardLoader {
    static List<EReward> getBuyModels(YamlConfiguration config, String item) {
        List<EReward> rewards = new ArrayList<>();

        for (String string : config.getConfigurationSection("items." + item + ".buy-rewards").getKeys(true)) {

        }

        return rewards;
    }

    static List<EReward> getSellModels(YamlConfiguration config, String item) {
        List<EReward> rewards = new ArrayList<>();

        for (String reward : config.getConfigurationSection("items." + item + ".sell-rewards").getKeys(true)) {
            if (!reward.contains(".")) {

            }
        }

        return rewards;
    }

    private static List<ERewardType> getRewardType(String type) {
        List<ERewardType> rewardTypes = Configuration.getRewardTypes();

        for (ERewardType rewardType : Configuration.getRewardTypes()) {
            if (rewardType.getType().equals(type.toLowerCase())) {
                rewardTypes.add(rewardType);
            }
        }

        return rewardTypes;
    }
}
