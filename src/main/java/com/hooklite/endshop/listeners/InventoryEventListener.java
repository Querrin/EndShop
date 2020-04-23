package com.hooklite.endshop.listeners;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEventListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        MessageLogger.toPlayer((Player) event.getWhoClicked(), event.getClickedInventory().getType().toString());
    }
}
