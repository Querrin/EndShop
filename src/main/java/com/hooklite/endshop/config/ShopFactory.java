package com.hooklite.endshop.config;

import com.hooklite.endshop.commands.CommandStatus;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShopFactory {
    private static int maxSlot = 0;

    public static int getMaxSlot() {
        return maxSlot;
    }

    /**
     * Creates the shop models from a map of file names and configuration files.
     * @return A list of shop objects.
     */
    static List<Shop> getShops(Map<String, YamlConfiguration> map) {
        List<Shop> shops = new ArrayList<>();
        Iterator<YamlConfiguration> valueIterator = map.values().iterator();
        Iterator<String> keyIterator = map.keySet().iterator();

        for(int i = 0; i < map.size(); i++) {
            Shop shop = new Shop();
            YamlConfiguration yamlConfig = valueIterator.next();
            String shopFile = keyIterator.next();

            for(YamlConfiguration config : map.values()) {
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
                        ((ShopKey) rKey).setValue(shop, yamlConfig, i);
                    }
                }

                for(ConfigKey cKey : Configuration.getConfigKeys()) {
                    if(cKey instanceof ShopKey) {
                        ((ShopKey) cKey).setValue(shop, yamlConfig, i);
                    }
                }

                shop.pages = PageFactory.getPages(shop.title, ItemFactory.getItems(yamlConfig));
                shops.add(shop);
            }
            catch(InvalidConfigurationException e) {
                MessageSender.toConsole(ChatColor.RED + "FILE: " + Colors.loadColors(shopFile));
                MessageSender.toConsole(ChatColor.RED + "ERROR: " + e.getMessage());
                e.printStackTrace();

                Configuration.closeShopMenus(Bukkit.getOnlinePlayers());
                CommandStatus.disableShopCommand();
                break;
            }
        }


        return shops;
    }
}
