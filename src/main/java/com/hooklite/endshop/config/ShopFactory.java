package com.hooklite.endshop.config;

import com.hooklite.endshop.config.interfaces.ConfigKey;
import com.hooklite.endshop.config.interfaces.RequiredKey;
import com.hooklite.endshop.config.interfaces.ShopKey;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.logging.Colors;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ShopFactory {
    private static int maxSlot = 0;

    public static int getMaxSlot() {
        return maxSlot;
    }

    static List<Shop> getShops(List<YamlConfiguration> configs) {
        List<Shop> shops = new ArrayList<>();

        for(int i = 0; i < configs.size(); i++) {
            Shop shop = new Shop();

            for(YamlConfiguration config : configs) {
                int value = config.getInt("slot", 0);

                if(value != 0) {
                    if(value > maxSlot) {
                        maxSlot = value;
                    }
                }
            }

            try {
                for(RequiredKey rKey : Configuration.getRequiredKeys()) {
                    if(rKey instanceof ShopKey) {
                        ((ShopKey) rKey).setValue(shop, configs.get(i), i);
                    }
                }

                for(ConfigKey cKey : Configuration.getConfigKeys()) {
                    if(cKey instanceof ShopKey) {
                        ((ShopKey) cKey).setValue(shop, configs.get(i), i);
                    }
                }

                shop.pages = PageFactory.getPages(shop, ItemFactory.getItems(configs.get(i)));
                shops.add(shop);
            }
            catch(InvalidConfigurationException e) {
                MessageSender.toConsole(ChatColor.RED + "SHOP: " + Colors.loadColors(configs.get(i).getString("title")));
                MessageSender.toConsole(ChatColor.RED + "ERROR: " + e.getMessage());
                e.printStackTrace();
                Bukkit.getPluginManager().disablePlugin(Configuration.getPlugin());
                break;
            }
        }


        return shops;
    }
}
