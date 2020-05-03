package com.hooklite.endshop.config;

import com.hooklite.endshop.config.item.EItemBuyable;
import com.hooklite.endshop.config.item.EItemSellable;
import com.hooklite.endshop.data.conditions.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

class ERequirementFactory {
    ERequirement getRequirement(YamlConfiguration configuration, String itemSection, EAction action) throws InvalidConfigurationException {
        if(required(configuration, itemSection, action)) {
            String value = configuration.getString(getKeyPath(itemSection, action));
            ERequirement req = getRequirement(value);

            if(req == null)
                throw new InvalidConfigurationException("Requirement type improperly configured!");

            return req;
        }

        return null;
    }

    String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + ".buy-req.type";

        return "items." + itemSection + ".sell-req.type";
    }

    boolean required(YamlConfiguration configuration, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new EItemBuyable().getValue(configuration, itemSection);

        return new EItemSellable().getValue(configuration, itemSection);
    }

    private ERequirement getRequirement(String type) {
        for(ERequirement req : Configuration.getRequirements()) {
            if(req.getType().equalsIgnoreCase(type))
                return req.getInstance();
        }

        return null;
    }
}
