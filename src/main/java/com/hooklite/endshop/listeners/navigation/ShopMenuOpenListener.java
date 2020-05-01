package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.config.MenuLoader;
import com.hooklite.endshop.events.ShopMenuOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopMenuOpenListener implements Listener {
    @EventHandler
    public void onShopMenuOpen(ShopMenuOpenEvent event) {
        event.getWhoOpened().openInventory(MenuLoader.getShopMenu(Configuration.getShops(), event.getWhoOpened()));
    }
}
