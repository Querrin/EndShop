package com.hooklite.endshop.config.item.requirement;

import com.hooklite.endshop.config.interfaces.RequirementKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.requirements.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class RequirementReq implements RequirementKey {

    @Override
    public ERequirement setValue(ERequirement req, Item item, YamlConfiguration config, String itemSection, EAction action) throws InvalidConfigurationException {
        if(required(config, itemSection, action)) {
            req.setRequirement(config.getString(getKeyPath(itemSection, action)));
        }

        return req;
    }

    @Override
    public String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + ".buy-req.req";

        return "items." + itemSection + ".sell-req.req";
    }

    @Override
    public boolean required(YamlConfiguration config, String itemSection, EAction action) {
        if(action == EAction.BUY)
            return new ItemBuyable().getValue(config, itemSection);

        return new ItemSellable().getValue(config, itemSection);
    }

    @Override
    public String getKey() {
        return "req";
    }
}
