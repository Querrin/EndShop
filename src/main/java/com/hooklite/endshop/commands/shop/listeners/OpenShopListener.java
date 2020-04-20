package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.events.OpenShopEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OpenShopListener implements Listener {
    @EventHandler
    public void onOpenShop(OpenShopEvent event) {
        event.getWhoOpened().openInventory(event.getShop().getShopItemInventories().get(0));
    }
}
