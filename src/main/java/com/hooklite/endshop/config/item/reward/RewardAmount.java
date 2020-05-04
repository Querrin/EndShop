package com.hooklite.endshop.config.item.reward;

import com.hooklite.endshop.config.interfaces.RewardKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class RewardAmount implements RewardKey {
    @Override
    public EReward setValue(EReward reward, Item item, YamlConfiguration config, String itemSection, EAction action) throws InvalidConfigurationException {
        if(required(config, itemSection, action)) {
            reward.setAmount(config.getInt(getKeyPath(itemSection, action), 1));
        }

        return reward;
    }

    @Override
    public String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + ".buy-reward.amount";

        return "items." + itemSection + ".sell-reward.amount";
    }

    @Override
    public boolean required(YamlConfiguration configuration, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new ItemBuyable().getValue(configuration, itemSection);

        return new ItemSellable().getValue(configuration, itemSection);
    }

    @Override
    public String getKey() {
        return "amount";
    }
}
