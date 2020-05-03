package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.interfaces.EItemKey;
import com.hooklite.endshop.data.models.EItem;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class EItemDisplayItem implements EItemKey {
    @Override
    public void setValue(EItem item, YamlConfiguration configuration, String itemSection, int ignore) throws InvalidConfigurationException {
        String value = configuration.getString("display-item");

        if(value != null) {
            Material material = Material.matchMaterial(value);

            if(material == null)
                throw new InvalidConfigurationException("Item type not found!");

            ItemStack displayItem = new ItemStack(material);
            setMeta(displayItem);

            item.displayItem = displayItem;
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

    private void setMeta(ItemStack item) {

    }
}
