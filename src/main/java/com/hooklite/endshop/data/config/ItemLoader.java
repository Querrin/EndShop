package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.rewards.RewardAction;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ItemLoader {

    /**
     * Deserializes the data from a config file.
     *
     * @param config The configuration file.
     * @return A list of EItem data models.
     * @throws InvalidConfigurationException If the configuration file is improperly configured.
     * @throws NullPointerException          If the configuration section doesn't exist.
     */
    static List<EItem> getModels(YamlConfiguration config) throws InvalidConfigurationException, NullPointerException {
        List<EItem> items = new ArrayList<>();
        Set<String> itemKeys = config.getConfigurationSection("items").getKeys(false);

        if (!itemKeys.isEmpty()) {
            for (String item : itemKeys) {
                EItem eItem = new EItem();

                Material displayItem = Material.matchMaterial(config.getString(String.format("items.%s.display-item", item)));
                List<String> description = new ArrayList<>();

                if (displayItem == null)
                    throw new InvalidConfigurationException(String.format("display-item in item \"%s\" is improperly configured!", item));

                if (!config.getStringList("description").isEmpty()) {
                    description = config.getStringList("description");

                    for (int i = 0; i < description.size(); i++) {
                        description.set(i, Colors.loadColors(description.get(i)));
                    }
                }

                eItem.name = config.getString(Colors.loadColors(String.format("items.%s.name", item)));
                eItem.description = description;
                eItem.slot = config.getInt(String.format("items.%s.slot", item));
                eItem.displayItem = displayItem;
                eItem.buyPrice = config.getDouble(String.format("items.%s.buy-price", item));
                eItem.sellPrice = config.getDouble(String.format("items.%s.sell-price", item));
                eItem.buyReward = RewardLoader.getModel(config, item, RewardAction.BUY);
                eItem.sellReward = RewardLoader.getModel(config, item, RewardAction.SELL);

                items.add(eItem);
            }
        }

        return items;
    }
}
