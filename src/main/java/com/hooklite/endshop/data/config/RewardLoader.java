package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.data.rewards.types.ERewardType;
import org.bukkit.configuration.file.YamlConfiguration;

class RewardLoader {
    static EReward getBuyModel(YamlConfiguration config, String item) {
        ERewardType rewardType = getRewardType(config.getString("items." + item + ".buy-reward.type"));
        String rewardString = config.getString("items." + item + ".buy-reward.reward");

        return getReward(rewardType, rewardString);
    }

    static EReward getSellModel(YamlConfiguration config, String item) {
        ERewardType rewardType = getRewardType(config.getString("items." + item + ".sell-reward.type"));
        String rewardString = config.getString("items." + item + ".buy-reward.reward");

        return getReward(rewardType, rewardString);
    }

    private static EReward getReward(ERewardType rewardType, String rewardString) {
        EReward reward = null;

        for (ERewardType type : Configuration.getRewardTypes()) {
            if (rewardType.equals(type)) {
                reward = type.getRewardObject();
                reward.setReward(rewardString);
                break;
            }
        }

        return reward;
    }

    private static ERewardType getRewardType(String type) {
        ERewardType eRewardType = null;

        for (ERewardType rewardType : Configuration.getRewardTypes()) {
            if (rewardType.getType().equals(type.toLowerCase())) {
                eRewardType = rewardType;
            }
        }

        return eRewardType;
    }
}
