package com.hooklite.endshop.config;

import com.hooklite.endshop.config.interfaces.ConfigKey;
import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.EAction;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class ItemFactory {
    static List<Item> getItems(YamlConfiguration config, Shop shop) throws InvalidConfigurationException {
        List<Item> items = new ArrayList<>();
        ConfigurationSection itemSection = config.getConfigurationSection("items");

        if(itemSection != null) {
            Set<String> keys = itemSection.getKeys(false);
            Iterator<String> keyIteration = keys.iterator();
            Item item = new Item();


            for(int i = 0; i < keys.size(); i++) {
                String sectionKey = keyIteration.next();

                item.buyReward = RewardFactory.getReward(config, item, sectionKey, EAction.BUY);
                item.sellReward = RewardFactory.getReward(config, item, sectionKey, EAction.SELL);
                item.buyReq = RequirementFactory.getRequirement(config, item, sectionKey, EAction.BUY);
                item.sellReq = RequirementFactory.getRequirement(config, item, sectionKey, EAction.SELL);

                for(RequiredKey rKey : Configuration.getRequiredKeys()) {
                    if(rKey instanceof ItemKey) {
                        ((ItemKey) rKey).setValue(shop, item, config, sectionKey, i);
                    }
                }

                for(ConfigKey cKey : Configuration.getConfigKeys()) {
                    if(cKey instanceof ItemKey) {
                        ((ItemKey) cKey).setValue(shop, item, config, sectionKey, i);
                    }
                }
            }

            items.add(item);
        }

        return items;
    }
}
