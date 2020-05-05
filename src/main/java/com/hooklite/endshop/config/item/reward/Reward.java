package com.hooklite.endshop.config.item.reward;

import com.hooklite.endshop.config.interfaces.RewardKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Reward implements RewardKey {

    @Override
    public com.hooklite.endshop.data.rewards.Reward setValue(com.hooklite.endshop.data.rewards.Reward reward, Item item, YamlConfiguration config, String itemSection, Action action) throws InvalidConfigurationException {
        String value = config.getString(getKeyPath(itemSection, action));

        if(required(config, itemSection, action)) {
            if(value == null || value.isEmpty())
                throw new InvalidConfigurationException("Reward improperly configured!");

            reward.setReward(value);
        }

        return reward;
    }

    @Override
    public String getKeyPath(String itemSection, Action action) {
        if(action == Action.BUY)
            return "items." + itemSection + ".buy-reward.reward";

        return "items." + itemSection + ".sell-reward.reward";
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
