package com.hooklite.endshop.config.item.requirement;

import com.hooklite.endshop.config.interfaces.RequirementKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.requirements.Requirement;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.configuration.file.YamlConfiguration;

public class RequirementAmount implements RequirementKey {
    @Override
    public Requirement setValue(Requirement req, YamlConfiguration config, String itemKey, Action action) {
        if(required(config, itemKey, action)) {
            req.setAmount(config.getInt(getKeyPath(itemKey, action), 1));
        }

        return req;
    }

    @Override
    public String getKeyPath(String itemSection, Action action) {
        if(action == Action.BUY)
            return "items." + itemSection + ".buy-req.amount";

        return "items." + itemSection + ".sell-req.amount";
    }

    @Override
    public boolean required(YamlConfiguration config, String itemSection, Action action) {
        if(action == Action.BUY)
            return new ItemBuyable().getValue(config, itemSection);

        return new ItemSellable().getValue(config, itemSection);
    }

    @Override
    public String getKey() {
        return null;
    }
}
