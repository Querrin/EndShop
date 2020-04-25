package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.data.rewards.RewardAction;
import com.hooklite.endshop.data.rewards.types.ERewardType;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

class RewardLoader {
    static EReward getModel(YamlConfiguration config, String item, RewardAction action) throws InvalidConfigurationException {
        ERewardType rewardType;
        String rewardString;

        if (action == RewardAction.BUY) {
            rewardType = getRewardType(config.getString("items." + item + ".buy-reward.type"));
            rewardString = config.getString("items." + item + ".buy-reward.reward");
        } else {
            rewardType = getRewardType(config.getString("items." + item + ".sell-reward.type"));
            rewardString = config.getString("items." + item + ".sell-reward.reward");
        }

        if (rewardType == null || rewardString == null)
            throw new InvalidConfigurationException(String.format("Rewards of %s are improperly configured!", item));

        EReward reward = getReward(rewardType, rewardString);
        reward.setRewardAction(action);

        return reward;
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
