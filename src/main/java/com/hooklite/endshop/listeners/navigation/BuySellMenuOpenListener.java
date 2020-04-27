package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.config.InventoryLoader;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.BuySellMenuOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BuySellMenuOpenListener implements Listener {
    @EventHandler
    public void onBuySellMenuOpen(BuySellMenuOpenEvent event) {
        EItem item = null;
        boolean matchFound = false;

        for (EShop shop : Configuration.getShops()) {
            if (matchFound)
                break;

            for (EPage page : shop.pages) {
                if (matchFound)
                    break;

                for (EItem eItem : page.getItems()) {
                    if (eItem.displayItem.equals(event.getItem())) {
                        item = eItem;
                        matchFound = true;
                        break;
                    }
                }
            }
        }

        event.getWhoOpened().openInventory(InventoryLoader.getBuySellMenu(item));
    }
}
