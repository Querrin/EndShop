package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.PreviousPageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PreviousPageListener implements Listener {
    @EventHandler
    public void onPreviousPage(PreviousPageEvent event) {
        EShop shop = event.getShop();
        int previousPage = event.getPage().getNumber() - 1;

        if (!(previousPage < 0)) {
            event.getWhoClicked().openInventory(shop.pages.get(previousPage).getInventory());
        }
    }
}
