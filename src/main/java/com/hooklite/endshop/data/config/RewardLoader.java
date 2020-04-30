package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
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
    static EReward getModel(YamlConfiguration config, String item, EAction action) throws InvalidConfigurationException {
        String type;
        String reward;
        EReward eReward;

        // Gets the reward depending on the RewardAction type
        if(action == EAction.BUY) {
            type = config.getString("items." + item + ".buy-reward.type");
            reward = config.getString("items." + item + ".buy-reward.reward");
            eReward = getReward(type, reward);
        }
        else {
            type = config.getString("items." + item + ".sell-reward.type");
            reward = config.getString("items." + item + ".sell-reward.reward");
            eReward = getReward(type, reward);
        }

        if(eReward == null)
            throw new InvalidConfigurationException(String.format("Rewards of %s are improperly configured!", item));

        return eReward;
    }

    /**
     * Gets an reward object from a reward type.
     *
     * @param type         The type of reward.
     * @param rewardString The reward that will be given upon execution
     * @return An EReward object corresponding to the type.
     */
    private static EReward getReward(String type, String rewardString) throws InvalidConfigurationException {
        EReward reward = null;

        for(EReward eReward : Configuration.getRewards()) {
            if(eReward.getType().equals(type)) {
                reward = eReward.getInstance();
                reward.setReward(rewardString);
                break;
            }
        }

        return reward;
    }
}
