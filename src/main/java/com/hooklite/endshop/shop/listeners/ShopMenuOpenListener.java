package com.hooklite.endshop.shop.listeners;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.config.InventoryLoader;
import com.hooklite.endshop.shop.events.ShopMenuOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopMenuOpenListener implements Listener {
    @EventHandler
    public void onShopMenuOpen(ShopMenuOpenEvent event) {
        event.getWhoOpened().openInventory(InventoryLoader.getShopMenu(Configuration.getShops(), event.getWhoOpened()));
    }
}
