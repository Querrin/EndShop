package com.hooklite.endshop.config.item.requirement;

import com.hooklite.endshop.config.interfaces.ERequirementKey;
import com.hooklite.endshop.config.item.EItemBuyable;
import com.hooklite.endshop.config.item.EItemSellable;
import com.hooklite.endshop.data.conditions.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.file.YamlConfiguration;

public class ERequirementAmount implements ERequirementKey {
    @Override
    public void setValue(ERequirement req, YamlConfiguration configuration, String itemSection, EAction action) {
        if(required(configuration, itemSection, action)) {
            req.setAmount(configuration.getInt(getKeyPath(itemSection, action), 1));
        }
    }

    @Override
    public String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + "buy-req.amount";

        return "items." + itemSection + "sell-req.amount";
    }

    @Override
    public boolean required(YamlConfiguration configuration, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new EItemBuyable().getValue(configuration, itemSection);

        return new EItemSellable().getValue(configuration, itemSection);
    }

    @Override
    public String getKey() {
        return null;
    }
}
