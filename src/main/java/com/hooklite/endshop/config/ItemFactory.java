package com.hooklite.endshop.config;

import com.hooklite.endshop.config.interfaces.ConfigKey;
import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ItemFactory {
    private static int maxSlot = 0;

    public static int getMaxSlot() {
        return maxSlot;
    }

    static List<Item> getItems(YamlConfiguration config) throws InvalidConfigurationException {
        List<Item> items = new ArrayList<>();
        ConfigurationSection itemSection = config.getConfigurationSection("items");
        maxSlot = 0;

        if(itemSection != null) {
            Set<String> keys = itemSection.getKeys(false);
            Iterator<String> keyIteration = keys.iterator();

            for(String key : keys) {
                int value = config.getInt("items." + key + ".slot", 0);

                if(value != 0 && value > maxSlot) {
                    maxSlot = value;
                }
            }

            Item item;

            for(int i = 0; i < keys.size(); i++) {
                item = new Item();
                String sectionKey = keyIteration.next();

                try {
                    item.buyReward = RewardFactory.getReward(config, item, sectionKey, Action.BUY);
                    item.sellReward = RewardFactory.getReward(config, item, sectionKey, Action.SELL);
                    item.buyReq = RequirementFactory.getRequirement(config, item, sectionKey, Action.BUY);
                    item.sellReq = RequirementFactory.getRequirement(config, item, sectionKey, Action.SELL);

                    for(RequiredKey rKey : Configuration.getRequiredKeys()) {
                        if(rKey instanceof ItemKey) {
                            ((ItemKey) rKey).setValue(item, config, sectionKey, i);
                        }
                    }

                    for(ConfigKey cKey : Configuration.getConfigKeys()) {
                        if(cKey instanceof ItemKey) {
                            ((ItemKey) cKey).setValue(item, config, sectionKey, i);
                        }
                    }

                    items.add(item);
                }
                catch(InvalidConfigurationException e) {
                    MessageSender.toConsole(ChatColor.RED + "ITEM: " + sectionKey);
                    throw e;
                }
            }
        }

        return items;
    }

    public static Item getItemMatch(ItemStack eventItem) {
        for(Shop shop : Configuration.getShops()) {
            for(Page page : shop.pages) {
                for(Item eItem : page.getItems()) {
                    if(eItem.displayItem.equals(eventItem)) {
                        return eItem;
                    }
                }
            }
        }

        return null;
    }
}
