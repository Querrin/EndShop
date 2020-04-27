package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.NextPageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NextPageListener implements Listener {
    @EventHandler
    public void onNextPage(NextPageEvent event) {
        EShop shop = event.getShop();
        int nextPage = event.getPage().getNumber() + 1;

        if (!(nextPage > shop.pages.size() - 1)) {
            event.getWhoClicked().openInventory(shop.pages.get(nextPage).getInventory());
        }
    }
}
