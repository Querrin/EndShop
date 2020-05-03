package com.hooklite.endshop.config;

import com.hooklite.endshop.data.conditions.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

class RequirementLoader {
    static ERequirement getModel(YamlConfiguration config, String item, EAction action, boolean isAllowed) throws InvalidConfigurationException {
        if(!isAllowed)
            return null;

        ERequirement requirement;
        String req;

        if(action == EAction.BUY) {
            requirement = getRequirement(config.getString("items." + item + ".buy-req.type"));
            req = config.getString("items." + item + ".buy-req.req");

        }
        else {
            requirement = getRequirement(config.getString("items." + item + ".sell-req.type"));
            req = config.getString("items." + item + ".sell-req.req");
        }


        if(requirement == null || req == null)
            throw new InvalidConfigurationException(String.format("The buy-req/sell-req in item \"%s\" are improperly configured!", item));

        requirement.setRequirement(req);

        return requirement;
    }

    private static ERequirement getRequirement(String type) {
        for(ERequirement req : Configuration.getRequirements()) {
            if(req.getType().equalsIgnoreCase(type))
                return req.getInstance();
        }

        return null;
    }
}
