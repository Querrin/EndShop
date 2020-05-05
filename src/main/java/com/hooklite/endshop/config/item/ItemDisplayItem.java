package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.ItemKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemDisplayItem implements ItemKey, RequiredKey {
    @Override
    public void setValue(Shop shop, Item item, YamlConfiguration configuration, String itemSection, int ignore) throws InvalidConfigurationException {
        String value = configuration.getString("display-item");

        if(value != null) {
            Material material = Material.matchMaterial(value);

            if(material == null)
                throw new InvalidConfigurationException("Item type not found!");

            item.displayItem = setMeta(new ItemStack(material), shop, item);
        }
        else {
            if(required())
                throw new InvalidConfigurationException("Value must be set!");
        }
    }

    @Override
    public String getKeyPath(String itemSection) {
        return "items." + itemSection + ".display-item";
    }

    @Override
    public String getKey() {
        return "display-item";
    }

    @Override
    public boolean required() {
        return true;
    }

    private ItemStack setMeta(ItemStack itemStack, Shop shop, Item item) {
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;

        meta.setDisplayName(shop.title);
        meta.setLore(shop.description);

        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
