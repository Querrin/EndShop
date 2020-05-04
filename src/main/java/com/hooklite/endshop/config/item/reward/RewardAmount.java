package com.hooklite.endshop.config.item.reward;

import com.hooklite.endshop.config.interfaces.RewardKey;
import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class RewardAmount implements RewardKey {
    @Override
    public void setValue(EReward reward, YamlConfiguration configuration, String itemSection, EAction action) throws InvalidConfigurationException {

    }

    @Override
    public String getKeyPath(String itemSection, EAction action) {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }
}
