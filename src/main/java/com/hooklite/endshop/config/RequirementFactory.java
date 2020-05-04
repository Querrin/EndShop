package com.hooklite.endshop.config;

import com.hooklite.endshop.config.interfaces.ConfigKey;
import com.hooklite.endshop.config.interfaces.RequirementKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.requirements.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

class RequirementFactory {
    static ERequirement getRequirement(YamlConfiguration config, Item item, String itemSection, EAction action) throws InvalidConfigurationException {
        if(required(config, itemSection, action)) {
            String value = config.getString(getKeyPath(itemSection, action));
            ERequirement req = getRequirement(value);

            if(req == null)
                throw new InvalidConfigurationException("Requirement type improperly configured!");

            for(ConfigKey rKey : Configuration.getRequiredKeys()) {
                if(rKey instanceof RequirementKey) {
                    ((RequirementKey) rKey).setValue(req, item, config, itemSection, action);
                }
            }

            for(ConfigKey cKey : Configuration.getConfigKeys()) {
                if(cKey instanceof RequirementKey) {
                    ((RequirementKey) cKey).setValue(req, item, config, itemSection, action);
                }
            }

            return req;
        }

        return null;
    }

    static String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + ".buy-req.type";

        return "items." + itemSection + ".sell-req.type";
    }

    static boolean required(YamlConfiguration configuration, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new ItemBuyable().getValue(configuration, itemSection);

        return new ItemSellable().getValue(configuration, itemSection);
    }

    static private ERequirement getRequirement(String type) {
        for(ERequirement req : Configuration.getRequirements()) {
            if(req.getType().equalsIgnoreCase(type))
                return req.getInstance();
        }

        return null;
    }
}
