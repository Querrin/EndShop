package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.data.rewards.RewardAction;
import com.hooklite.endshop.data.rewards.types.ERewardType;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

class RewardLoader {

    /**
     * Deserializes the reward from the item section in an configuration file, depending on the reward action.
     *
     * @param config A configuration file that the reward will be read from.
     * @param item   An item that contains a reward.
     * @param action If the reward is for buy/sell.
     * @return An object of EReward with all values configured.
     * @throws InvalidConfigurationException If the configuration file is improperly configured.
     */
    static EReward getModel(YamlConfiguration config, String item, RewardAction action) throws InvalidConfigurationException {
        ERewardType rewardType;
        String rewardString;

        // Gets the reward depending on the RewardAction type
        if (action == RewardAction.BUY) {
            rewardType = getRewardType(config.getString("items." + item + ".buy-reward.type"));
            rewardString = config.getString("items." + item + ".buy-reward.reward");
        } else {
            rewardType = getRewardType(config.getString("items." + item + ".sell-reward.type"));
            rewardString = config.getString("items." + item + ".sell-reward.reward");
        }

        if (rewardType == null || rewardString == null)
            throw new InvalidConfigurationException(String.format("Rewards of %s are improperly configured!", item));

        return getReward(rewardType, rewardString);
    }

    /**
     * Gets an reward object from a reward type.
     *
     * @param rewardType   The reward type.
     * @param rewardString The reward string from the configuration file.
     * @return An EReward object corresponding to the type.
     */
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

    /**
     * Gets the type of the reward from a given string.
     *
     * @param type The reward string
     * @return An object of ERewardType that corresponds with the reward type string.
     */
    private static ERewardType getRewardType(String type) throws NullPointerException {
        ERewardType eRewardType = null;

        for (ERewardType rewardType : Configuration.getRewardTypes()) {
            if (rewardType.getType().equals(type.toLowerCase())) {
                eRewardType = rewardType;
            }
        }

        return eRewardType;
    }
}
