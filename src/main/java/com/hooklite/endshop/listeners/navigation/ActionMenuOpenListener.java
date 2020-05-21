package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.config.ItemFactory;
import com.hooklite.endshop.data.menus.BuyInventory;
import com.hooklite.endshop.data.menus.BuySellInventory;
import com.hooklite.endshop.data.menus.SellInventory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.events.ActionMenuOpenEvent;
import com.hooklite.endshop.holders.ItemMenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ActionMenuOpenListener implements Listener {
    @EventHandler
    public void onActionMenuOpen(ActionMenuOpenEvent event) {
        Item item = findMatch(event.getInventoryHolder().PAGE, event.getItemStack());
        Player player = event.getWhoOpened();

        if(item.buyable && item.sellable)
            player.openInventory(new BuySellInventory().getMenu(item, event.getInventoryHolder().getInventory(), player));
        else if(item.buyable)
            player.openInventory(new BuyInventory().getMenu(item, event.getInventoryHolder().getInventory(), player));
        else if(item.sellable)
            player.openInventory(new SellInventory().getMenu(item, event.getInventoryHolder().getInventory(), player));
    }

    private Item findMatch(Page page, ItemStack itemStack) {
        for(Item item : page.getItems()) {
            if(item.displayItem == itemStack)
                return item;
        }

        throw new IllegalArgumentException("Invalid inventory argument");
    }
}
