package com.hooklite.endshop.config.item.reward;

import com.hooklite.endshop.config.interfaces.RewardKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.data.rewards.Reward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;


public class RewardConfigKey implements RewardKey {

    @Override
    public Reward setValue(Reward reward, YamlConfiguration config, String itemKey, Action action) throws InvalidConfigurationException {
        String value = config.getString(getKeyPath(itemKey, action));

        if(required(config, itemKey, action)) {
            if(value == null || value.isEmpty())
                throw new InvalidConfigurationException("Reward improperly configured!");

            reward.setReward(value);
        }

        return reward;
    }

    @Override
    public String getKeyPath(String itemKey, Action action) {
        if(action == Action.BUY)
            return "items." + itemKey + ".buy-reward.reward";

        return "items." + itemKey + ".sell-reward.reward";
    }

    @Override
    public boolean required(YamlConfiguration config, String itemSection, Action action) {
        if(action == Action.BUY)
            return new ItemBuyable().getValue(config, itemSection);

        return new ItemSellable().getValue(config, itemSection);
    }

    @Override
    public String getKey() {
        return null;
    }
}
