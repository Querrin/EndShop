package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.config.MenuLoader;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.shop.BuySellMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EBuySellMenu implements EActionMenu {

    @Override
    public Inventory getMenu(Item item, Player player) {
        Inventory inventory = Bukkit.createInventory(new BuySellMenu(), 36, item.name);

        inventory.setItem(9, MenuLoader.getSellItem(item, item.sellReq.getMaxAmount(player)));
        inventory.setItem(10, MenuLoader.getSellItem(item, 64));
        inventory.setItem(11, MenuLoader.getSellItem(item, 32));
        inventory.setItem(12, MenuLoader.getSellItem(item, 1));
        inventory.setItem(13, item.displayItem);
        inventory.setItem(14, MenuLoader.getBuyItem(item, 1));
        inventory.setItem(15, MenuLoader.getBuyItem(item, 32));
        inventory.setItem(16, MenuLoader.getBuyItem(item, 64));
        inventory.setItem(17, MenuLoader.getBuyItem(item, item.buyReq.getMaxAmount(player)));

        inventory.setItem(31, MenuLoader.getBackItem());

        return inventory;
    }
}
