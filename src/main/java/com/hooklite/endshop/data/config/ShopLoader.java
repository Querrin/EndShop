package com.hooklite.endshop.data.config;

import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.logging.Colors;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ShopLoader {

    /**
     * Deserializes and creates a list of shop models from the configuration file.
     *
     * @param shopConfigs A list of configuration files from which objects will be created.
     * @return A list of EShop data models.
     * @throws InvalidConfigurationException If the configuration is improperly configured.
     */
    static List<EShop> getModels(List<YamlConfiguration> shopConfigs) throws InvalidConfigurationException {
        List<EShop> shops = new ArrayList<>();
        for(YamlConfiguration config : shopConfigs) {
            EShop shop = new EShop();
            Material displayItemMaterial;

            if(config.getString("title") != null)
                shop.title = Colors.loadColors(config.getString("title"));
            else
                throw new InvalidConfigurationException("A title in a shop is improperly configured!");

            // Gets the display item material
            try {
                displayItemMaterial = Material.matchMaterial(Objects.requireNonNull(config.getString("display-item")));
            }
            catch(NullPointerException e) {
                throw new InvalidConfigurationException(String.format("display-item in shop \"%s\" is improperly configured!", shop.title));
            }

            List<String> description = new ArrayList<>();

            if(displayItemMaterial == null)
                throw new InvalidConfigurationException(String.format("display-item of shop \"%s\" is improperly configured!", config.getName()));

            if(!config.getStringList("description").isEmpty()) {
                description = config.getStringList("description");

                for(int i = 0; i < description.size(); i++) {
                    description.set(i, Colors.loadColors(description.get(i)));
                }
            }

            // Loads the required values into a new instance of EShop
            shop.description = description;
            shop.slot = config.getInt("slot");
            shop.config = config;
            shop.pages = PageLoader.getModels(ItemLoader.getModels(config), shop);

            for(int i = 0; i < shop.pages.size(); i++) {
                PageLoader.setInventory(shop, i);
            }

            ItemStack displayItem = new ItemStack(displayItemMaterial);
            ItemMeta meta = displayItem.getItemMeta();
            meta.setDisplayName(shop.title);
            meta.setLore(shop.description);
            displayItem.setItemMeta(meta);

            shop.displayItem = displayItem;

            shops.add(shop);
        }

        return shops;
    }
}
