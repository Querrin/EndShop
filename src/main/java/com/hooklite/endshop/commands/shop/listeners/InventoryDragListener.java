package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.Shop;
import com.hooklite.endshop.commands.shop.gui.ShopsGui;
import com.hooklite.endshop.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory().equals(ShopsGui.getInventory())) {
            event.setCancelled(true);
        } else {
            for (Shop shop : Configuration.getShops()) {
                for (Inventory inventory : shop.getShopInventories()) {
                    if (event.getInventory().equals(inventory)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
