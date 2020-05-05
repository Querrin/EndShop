package com.hooklite.endshop.config.item.reward;

import com.hooklite.endshop.config.interfaces.RewardKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Reward implements RewardKey {

    @Override
    public EReward setValue(EReward reward, Item item, YamlConfiguration config, String itemSection, EAction action) throws InvalidConfigurationException {
        String value = config.getString(getKeyPath(itemSection, action));

        if(required(config, itemSection, action)) {
            if(value == null || value.isEmpty())
                throw new InvalidConfigurationException("Reward improperly configured!");

            reward.setReward(value);
        }

        return reward;
    }

    @Override
    public String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + ".buy-reward.reward";

        return "items." + itemSection + ".sell-reward.reward";
    }

    @Override
    public boolean required(YamlConfiguration config, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new ItemBuyable().getValue(config, itemSection);

        return new ItemSellable().getValue(config, itemSection);
    }

    @Override
    public String getKey() {
        return null;
    }
}
