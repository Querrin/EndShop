package com.hooklite.endshop.config.shop;

import com.hooklite.endshop.config.interfaces.EShopKey;
import com.hooklite.endshop.data.models.EShop;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class EShopDisplayItem implements EShopKey {
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
    public void setValue(EShop shop, YamlConfiguration configuration, int ignore) throws InvalidConfigurationException {
        String value = configuration.getString("display-item");

        if(value != null) {
            Material material = Material.matchMaterial(value);

            if(material == null)
                throw new InvalidConfigurationException("Item type not found!");

            shop.displayItem = new ItemStack(material);
        }
        else {
            if(required())
                throw new InvalidConfigurationException("Value must be set!");
        }
    }
}