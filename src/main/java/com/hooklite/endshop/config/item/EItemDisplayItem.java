package com.hooklite.endshop.config.item;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.config.interfaces.EItemKey;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.data.models.persistance.EItemTagType;
import com.hooklite.endshop.data.models.persistance.EShopTagType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class EItemDisplayItem implements EItemKey {
    @Override
    public void setValue(EShop shop, EItem item, YamlConfiguration configuration, String itemSection, int ignore) throws InvalidConfigurationException {
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

    private ItemStack setMeta(ItemStack itemStack, EShop shop, EItem item) {
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;
        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey shopKey = new NamespacedKey(Configuration.getPlugin(), "shop");
        NamespacedKey itemKey = new NamespacedKey(Configuration.getPlugin(), "item");

        meta.setDisplayName(shop.title);
        meta.setLore(shop.description);

        container.set(shopKey, EShopTagType.getInstance(), shop);
        container.set(itemKey, EItemTagType.getInstance(), item);

        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
