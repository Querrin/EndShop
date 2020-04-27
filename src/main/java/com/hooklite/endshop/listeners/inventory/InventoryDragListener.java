package com.hooklite.endshop.listeners.inventory;

import com.hooklite.endshop.shop.BuySellMenu;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory.getHolder() instanceof ShopMenu)
            event.setCancelled(true);
        else if (inventory.getHolder() instanceof ItemMenu)
            event.setCancelled(true);
        else if (inventory.getHolder() instanceof BuySellMenu)
            event.setCancelled(true);
    }
}
