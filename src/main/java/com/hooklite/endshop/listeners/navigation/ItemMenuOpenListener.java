package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.ItemMenuOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ItemMenuOpenListener implements Listener {
    @EventHandler
    public void onItemMenuOpen(ItemMenuOpenEvent event) {
        for(EShop shop : Configuration.getShops()) {
            if(shop.slot == event.getSlot()) {
                event.getWhoOpened().openInventory(shop.pages.get(0).getInventory());
                break;
            }
        }
    }
}
