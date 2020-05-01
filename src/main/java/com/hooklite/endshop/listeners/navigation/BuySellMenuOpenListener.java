package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.menus.EBuySellMenu;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.BuySellMenuOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class BuySellMenuOpenListener implements Listener {
    @EventHandler
    public void onBuySellMenuOpen(BuySellMenuOpenEvent event) {
        EItem item = getItemMatch(event.getItem());
        event.getWhoOpened().openInventory(new EBuySellMenu().getMenu(item, event.getWhoOpened()));
    }

    private EItem getItemMatch(ItemStack eventItem) {
        for(EShop shop : Configuration.getShops()) {
            for(EPage page : shop.pages) {
                for(EItem eItem : page.getItems()) {
                    if(eItem.displayItem.equals(eventItem)) {
                        return eItem;
                    }
                }
            }
        }

        return null;
    }
}
