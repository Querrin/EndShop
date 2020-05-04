package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.config.InventoryFactory;
import com.hooklite.endshop.events.ShopMenuOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopMenuOpenListener implements Listener {
    @EventHandler
    public void onShopMenuOpen(ShopMenuOpenEvent event) {
        event.getWhoOpened().openInventory(InventoryFactory.getShopMenu(Configuration.getShops(), event.getWhoOpened()));
    }
}
