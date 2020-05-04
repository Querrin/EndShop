package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.events.PageNavigation;
import com.hooklite.endshop.events.PageNavigationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PageNavigationListener implements Listener {
    @EventHandler
    public void onPageNavigation(PageNavigationEvent event) {
        Shop shop = null;
        Page page = null;
        boolean matchFound = false;

        for(Shop eShop : Configuration.getShops()) {
            if(matchFound)
                break;

            for(Page ePage : eShop.pages) {
                if(event.getInventory().equals(ePage.getInventory())) {
                    shop = eShop;
                    page = ePage;
                    matchFound = true;
                    break;
                }
            }
        }

        if(event.getDirection() == PageNavigation.NEXT_PAGE) {
            int nextPage = page.getNumber() + 1;

            if(!(nextPage > shop.pages.size() - 1)) {
                event.getWhoClicked().openInventory(shop.pages.get(nextPage).getInventory());
            }
        }
        else {
            int previousPage = page.getNumber() - 1;

            if(!(previousPage < 0)) {
                event.getWhoClicked().openInventory(shop.pages.get(previousPage).getInventory());
            }
        }
    }
}
