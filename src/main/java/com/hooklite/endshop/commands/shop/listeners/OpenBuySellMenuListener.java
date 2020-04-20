package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.BuySellMenu;
import com.hooklite.endshop.commands.shop.events.OpenBuySellMenuEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OpenBuySellMenuListener implements Listener {
    @EventHandler
    public void onOpenBuySellMenu(OpenBuySellMenuEvent event) {
        event.getWhoOpened().openInventory(new BuySellMenu().getInventory(event.getClickedItem()));
    }
}
