package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.data.menus.EBuyMenu;
import com.hooklite.endshop.data.menus.EBuySellMenu;
import com.hooklite.endshop.data.menus.ESellMenu;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.events.ActionMenuOpenEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ActionMenuOpenListener implements Listener {
    @EventHandler
    public void onActionMenuOpen(ActionMenuOpenEvent event) {
        Item item = getItemMatch(event.getItem());
        Player player = event.getWhoOpened();

        if(item.buyable && item.sellable)
            player.openInventory(new EBuySellMenu().getMenu(item, player));
        else if(item.buyable)
            player.openInventory(new EBuyMenu().getMenu(item, player));
        else if(item.sellable)
            player.openInventory(new ESellMenu().getMenu(item, player));
    }

    private Item getItemMatch(ItemStack eventItem) {
        for(Shop shop : Configuration.getShops()) {
            for(Page page : shop.pages) {
                for(Item item : page.getItems()) {
                    if(item.displayItem.equals(eventItem)) {
                        return item;
                    }
                }
            }
        }

        return null;
    }
}
