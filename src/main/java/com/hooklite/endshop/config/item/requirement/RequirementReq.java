package com.hooklite.endshop.config.item.requirement;

import com.hooklite.endshop.config.interfaces.RequirementKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.conditions.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class RequirementReq implements RequirementKey {

    @Override
    public void setValue(ERequirement req, YamlConfiguration configuration, String itemSection, EAction action) throws InvalidConfigurationException {
        if(required(configuration, itemSection, action)) {
            req.setRequirement(configuration.getString(getKeyPath(itemSection, action)));
        }
    }

    @Override
    public String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + "buy-req.req";

        return "items." + itemSection + "sell-req.req";
    }

    @Override
    public boolean required(YamlConfiguration configuration, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new ItemBuyable().getValue(configuration, itemSection);

        return new ItemSellable().getValue(configuration, itemSection);
    }

    @Override
    public String getKey() {
        return "req";
    }
}
