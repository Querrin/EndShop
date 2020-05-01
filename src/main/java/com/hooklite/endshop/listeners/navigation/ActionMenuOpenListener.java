package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.menus.EBuyMenu;
import com.hooklite.endshop.data.menus.EBuySellMenu;
import com.hooklite.endshop.data.menus.ESellMenu;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.ActionMenuOpenEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ActionMenuOpenListener implements Listener {
    @EventHandler
    public void onActionMenuOpen(ActionMenuOpenEvent event) {
        EItem item = getItemMatch(event.getItem());
        Player player = event.getWhoOpened();

        if(item.buyable && item.sellable)
            player.openInventory(new EBuySellMenu().getMenu(item, player));
        else if(item.buyable)
            player.openInventory(new EBuyMenu().getMenu(item, player));
        else if(item.sellable)
            player.openInventory(new ESellMenu().getMenu(item, player));
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
