package com.hooklite.endshop.config;

import com.hooklite.endshop.config.item.EItemBuyable;
import com.hooklite.endshop.config.item.EItemSellable;
import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

class ERewardFactory {
    EReward getReward(YamlConfiguration configuration, String itemSection, EAction action) throws InvalidConfigurationException {
        if(required(configuration, itemSection, action)) {
            String value = configuration.getString(getKeyPath(itemSection, action));
            EReward reward = getReward(value);

            if(reward == null)
                throw new InvalidConfigurationException("Reward type improperly configured!");

            return reward;
        }

        return null;
    }

    String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + "buy-reward.type";

        return "items." + itemSection + "sell-reward.type";
    }

    boolean required(YamlConfiguration configuration, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new EItemBuyable().getValue(configuration, itemSection);

        return new EItemSellable().getValue(configuration, itemSection);
    }

    private EReward getReward(String type) {
        for(EReward reward : Configuration.getRewards()) {
            if(reward.getType().equals(type))
                return reward.getInstance();
        }

        return null;
    }
}
