package com.hooklite.endshop.config.item.reward;

import com.hooklite.endshop.config.interfaces.RewardKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.data.rewards.Reward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class RewardAmount implements RewardKey {
    @Override
    public Reward setValue(Reward reward, YamlConfiguration config, String itemKey, Action action) throws InvalidConfigurationException {
        if(required(config, itemKey, action)) {
            reward.setAmount(config.getInt(getKeyPath(itemKey, action), 1));
        }

        return reward;
    }

    @Override
    public String getKeyPath(String itemKey, Action action) {
        if(action == Action.BUY)
            return "items." + itemKey + ".buy-reward.amount";

        return "items." + itemKey + ".sell-reward.amount";
    }

    @Override
    public boolean required(YamlConfiguration configuration, String itemSection, Action action) {
        if(action == Action.BUY)
            return new ItemBuyable().getValue(configuration, itemSection);

        return new ItemSellable().getValue(configuration, itemSection);
    }

    @Override
    public String getKey() {
        return "amount";
    }
}
