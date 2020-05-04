package com.hooklite.endshop.config.item.requirement;

import com.hooklite.endshop.config.interfaces.RequirementKey;
import com.hooklite.endshop.config.item.ItemBuyable;
import com.hooklite.endshop.config.item.ItemSellable;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.requirements.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.file.YamlConfiguration;

public class RequirementAmount implements RequirementKey {
    @Override
    public ERequirement setValue(ERequirement req, Item item, YamlConfiguration config, String itemSection, EAction action) {
        if(required(config, itemSection, action)) {
            req.setAmount(config.getInt(getKeyPath(itemSection, action), 1));
        }

        return req;
    }

    @Override
    public String getKeyPath(String itemSection, EAction action) {
        if(action == EAction.BUY)
            return "items." + itemSection + ".buy-req.amount";

        return "items." + itemSection + ".sell-req.amount";
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
