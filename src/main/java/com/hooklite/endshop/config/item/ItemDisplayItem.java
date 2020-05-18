package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemDisplayItem implements ItemKey, RequiredKey {
    @Override
    public void setValue(Item item, YamlConfiguration configuration, String itemKey, int ignore) throws InvalidConfigurationException {
        String value = configuration.getString(getKeyPath(itemKey));

        if(value != null) {
            Material material = Material.matchMaterial(value);

            if(material == null)
                throw new InvalidConfigurationException("Display item type invalid!");

            item.displayItem = setMeta(new ItemStack(material), item, configuration, itemKey);
        }
        else {
            if(required())
                throw new InvalidConfigurationException("Display item value must be set!");
        }
    }

    @Override
    public String getKeyPath(String itemKey) {
        return "items." + itemKey + ".display-item";
    }

    @Override
    public String getKey() {
        return "display-item";
    }

    @Override
    public boolean required() {
        return true;
    }

    private ItemStack setMeta(ItemStack itemStack, Item item, YamlConfiguration config, String itemSection) {
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;

        meta.setDisplayName(new ItemName().getValue(itemStack, config, itemSection));
        meta.setLore(item.description);

        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
