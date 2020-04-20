package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.ShopGui;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if(event.getInventory().equals(ShopGui.getInventory())) {
            MessageLogger.toConsole("Trueee");
            event.setCancelled(true);
        }
    }
}
