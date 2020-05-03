package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuLoader;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.shop.BuyMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ESellMenu implements EActionMenu {

    @Override
    public Inventory getMenu(EItem item, Player player) {
        Inventory inventory = Bukkit.createInventory(new BuyMenu(), 45);

        inventory.setItem(13, item.displayItem);

        inventory.setItem(19, MenuLoader.getSellItem(item, 1));
        inventory.setItem(20, MenuLoader.getSellItem(item, 16));
        inventory.setItem(21, MenuLoader.getSellItem(item, 32));
        inventory.setItem(22, MenuLoader.getSellItem(item, 64));
        inventory.setItem(23, MenuLoader.getSellItem(item, 128));
        inventory.setItem(24, MenuLoader.getSellItem(item, 256));
        inventory.setItem(25, MenuLoader.getSellMaxItem(item, player));

        inventory.setItem(40, MenuLoader.getBackItem());

        return inventory;
    }
}
