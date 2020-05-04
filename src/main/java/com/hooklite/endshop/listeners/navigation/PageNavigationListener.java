package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.models.persistance.PageTagType;
import com.hooklite.endshop.data.models.persistance.ShopTagType;
import com.hooklite.endshop.events.PageNavigation;
import com.hooklite.endshop.events.PageNavigationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class PageNavigationListener implements Listener {
    @EventHandler
    public void onPageNavigation(PageNavigationEvent event) {
        ItemMeta meta = event.getItem().getItemMeta();

        assert meta != null;
        PersistentDataContainer container = meta.getPersistentDataContainer();
        Shop shop = container.get(MenuItemFactory.SHOP_KEY, ShopTagType.getInstance());
        Page page = container.get(MenuItemFactory.PAGE_KEY, PageTagType.getInstance());

        assert shop != null && page != null;

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
