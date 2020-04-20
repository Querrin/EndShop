package com.hooklite.endshop.shop.listeners;

import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.shop.Shop;
import com.hooklite.endshop.shop.ShopGui;
import com.hooklite.endshop.shop.ShopItemGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Map;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory().equals(ShopGui.getInventory())) {
            if (event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
                for (Shop shop : Configuration.getShops()) {
                    if (shop.getSlot() == event.getSlot()) {
                        // TODO: Implement new inventory system and events
                        Player player = (Player) event.getWhoClicked();
                        Map map = ShopItemGui.getShopItemInventories();
                        ArrayList<Inventory> list = (ArrayList<Inventory>) map.get(shop);
                        player.openInventory(list.get(0));
                    }
                }
            }
            event.setCancelled(true);
        }

    }

}
