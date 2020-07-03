package com.hooklite.endshop.configuration.keys;

import com.hooklite.endshop.data.EItem;
import com.hooklite.endshop.data.EShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class SlotKey implements ShopKey, ItemKey {
    @Override
    public void loadValue(YamlConfiguration config, String itemName, EItem item) throws InvalidConfigurationException {
        int slot = (int) getValue(config, itemName);

        item.setSlot(slot);
    }

    @Override
    public String getItemKeyLocation(String itemName) {
        return String.format("items.%s.slot", itemName);
    }

    @Override
    public Object getValue(YamlConfiguration config, String itemName) {
        return config.getInt(getItemKeyLocation(itemName));
    }

    @Override
    public void loadValue(YamlConfiguration config, EShop shop) throws InvalidConfigurationException {

    }

    @Override
    public String getShopKeyLocation() {
        return "shop";
    }

    @Override
    public Object getValue(YamlConfiguration config) {
        return config.getInt(getShopKeyLocation());
    }
}
