package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.Shop;
import com.hooklite.endshop.commands.shop.gui.ShopsGui;
import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        System.out.println("YELLO");
        if(event.getInventory().equals(ShopsGui.getInventory())) {
            MessageLogger.toConsole("Trueee");
            event.setCancelled(true);
        }
        else {
            MessageLogger.toConsole("FALSE");
            for(Shop shop : Configuration.getShops()) {
                for(Inventory inventory : shop.getShopInventories()) {
                    if(event.getInventory().equals(inventory)) {
                        MessageLogger.toConsole("BIG TRUE");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
