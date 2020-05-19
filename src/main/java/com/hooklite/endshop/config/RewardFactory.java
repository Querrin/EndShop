package com.hooklite.endshop.config;

import com.hooklite.endshop.config.interfaces.ConfigKey;
import com.hooklite.endshop.config.interfaces.RewardKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.data.rewards.Reward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

class RewardFactory {

    /**
     * Creates the reward object from a configuration file.
     * @param config The configuration file.
     * @param itemKey The section key of item.
     * @param action If the reward is for buying or selling
     * @return A Reward object with its values.
     * @throws InvalidConfigurationException If any key value is invalid.
     */
    static Reward getReward(YamlConfiguration config, String itemKey, Action action) throws InvalidConfigurationException {
        if(required(config, itemKey, action)) {
            String value = config.getString(getKeyPath(itemKey, action));
            Reward reward = getReward(value);

            if(reward == null)
                throw new InvalidConfigurationException("Reward type improperly configured!");

            for(ConfigKey rKey : Configuration.getRequiredKeys()) {
                if(rKey instanceof RewardKey) {
                    ((RewardKey) rKey).setValue(reward, config, itemKey, action);
                }
            }

            for(ConfigKey cKey : Configuration.getConfigKeys()) {
                if(cKey instanceof RewardKey) {
                    ((RewardKey) cKey).setValue(reward, config, itemKey, action);
                }
            }

            return reward;
        }

        return null;
    }

    static String getKeyPath(String itemSection, Action action) {
        if(action == Action.BUY)
            return "items." + itemSection + ".buy-reward.type";

        return "items." + itemSection + ".sell-reward.type";
    }

    static boolean required(YamlConfiguration configuration, String itemSection, Action action) {
        if(action == Action.BUY)
            return new ItemBuyable().getValue(configuration, itemSection);

        return new ItemSellable().getValue(configuration, itemSection);
    }

    static private Reward getReward(String type) {
        for(Reward reward : Configuration.getRewards()) {
            if(reward.getType().equals(type))
                return reward.getInstance();
        }

        return null;
    }
}
