package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.ShopItem;
import com.hooklite.endshop.commands.shop.ShopItemGui;
import com.hooklite.endshop.commands.shop.events.BackEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BackListener implements Listener {
    @EventHandler
    public void onBack(BackEvent event) {
        for (ShopItem item : ShopItemGui.getAllShopItems()) {

        }
    }
}
