package com.hooklite.endshop.config.shop;

import com.hooklite.endshop.config.interfaces.ShopKey;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopDisplayItem implements ShopKey {
    @Override
    public String getKey() {
        return "display-item";
    }

    @Override
    public String getKeyPath() {
        return "display-item";
    }

    @Override
    public boolean required() {
        return true;
    }

    @Override
    public void setValue(Shop shop, YamlConfiguration configuration, int ignore) throws InvalidConfigurationException {
        String value = configuration.getString("display-item");

        if(value != null) {
            Material material = Material.matchMaterial(value);

            if(material == null)
                throw new InvalidConfigurationException("Display item type invalid!");

            shop.displayItem = setMeta(new ItemStack(material), shop);
        }
        else {
            if(required())
                throw new InvalidConfigurationException("Display item value must be set!");
        }
    }

    private ItemStack setMeta(ItemStack itemStack, Shop shop) {
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;

        meta.setDisplayName(shop.title);
        meta.setLore(shop.description);

        itemStack.setItemMeta(meta);

        return itemStack;
    }
}