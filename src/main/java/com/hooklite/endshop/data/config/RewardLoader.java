package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.data.rewards.types.ERewardType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

class RewardLoader {
    static List<EReward> getBuyModels(YamlConfiguration config, String item) {
        List<EReward> rewards = new ArrayList<>();

        for (String reward : config.getConfigurationSection("items." + item + ".buy-rewards").getKeys(true)) {
            if (!reward.contains(".")) {
                List<ERewardType> rewardTypes = getRewardTypes(reward);

                for (ERewardType rewardType : rewardTypes) {
                    rewards.add(rewardType.getRewardObject());
                }
            }
        }

        return rewards;
    }

    static List<EReward> getSellModels(YamlConfiguration config, String item) {
        List<EReward> rewards = new ArrayList<>();

        for (String reward : config.getConfigurationSection("items." + item + ".sell-rewards").getKeys(true)) {
            if (!reward.contains(".")) {
                List<ERewardType> rewardTypes = getRewardTypes(reward);

                for (ERewardType rewardType : rewardTypes) {
                    rewards.add(rewardType.getRewardObject());
                }
            }
        }

        return rewards;
    }

    private static List<ERewardType> getRewardTypes(String type) {
        List<ERewardType> rewardTypes = Configuration.getRewardTypes();

        for (ERewardType rewardType : Configuration.getRewardTypes()) {
            if (rewardType.getType().equals(type.toLowerCase())) {
                rewardTypes.add(rewardType);
            }
        }

        return rewardTypes;
    }
}
