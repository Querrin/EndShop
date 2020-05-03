package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuLoader;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.shop.BuyMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EBuyMenu implements EActionMenu {

    @Override
    public Inventory getMenu(EItem item, Player player) {
        Inventory inventory = Bukkit.createInventory(new BuyMenu(), 45);

        inventory.setItem(13, item.displayItem);

        inventory.setItem(19, MenuLoader.getBuyItem(item, 1));
        inventory.setItem(20, MenuLoader.getBuyItem(item, 16));
        inventory.setItem(21, MenuLoader.getBuyItem(item, 32));
        inventory.setItem(22, MenuLoader.getBuyItem(item, 64));
        inventory.setItem(23, MenuLoader.getBuyItem(item, 128));
        inventory.setItem(24, MenuLoader.getBuyItem(item, 256));
        inventory.setItem(25, MenuLoader.getBuyItem(item, 512));

        inventory.setItem(40, MenuLoader.getBackItem());

        return inventory;
    }
}
