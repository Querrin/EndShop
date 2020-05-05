package com.hooklite.endshop.config.item.requirement;

import com.hooklite.endshop.config.interfaces.RequirementKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.requirements.Requirement;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class RequirementReq implements RequirementKey {

    @Override
    public Requirement setValue(Requirement req, Item item, YamlConfiguration config, String itemSection, Action action) throws InvalidConfigurationException {
        if(required(config, itemSection, action)) {
            req.setRequirement(config.getString(getKeyPath(itemSection, action)));
        }

        return req;
    }

    @Override
    public String getKeyPath(String itemSection, Action action) {
        if(action == Action.BUY)
            return "items." + itemSection + ".buy-req.req";

        return "items." + itemSection + ".sell-req.req";
    }

    @Override
    public boolean required(YamlConfiguration config, String itemSection, Action action) {
        if(action == Action.BUY)
            return new ItemBuyable().getValue(config, itemSection);

        return new ItemSellable().getValue(config, itemSection);
    }

    @Override
    public String getKey() {
        return "req";
    }
}
