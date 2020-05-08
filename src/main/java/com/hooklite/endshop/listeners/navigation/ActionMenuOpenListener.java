package com.hooklite.endshop.listeners.navigation;

import com.hooklite.endshop.config.ItemFactory;
import com.hooklite.endshop.data.menus.BuyInventory;
import com.hooklite.endshop.data.menus.BuySellInventory;
import com.hooklite.endshop.data.menus.SellInventory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.events.ActionMenuOpenEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ActionMenuOpenListener implements Listener {
    @EventHandler
    public void onActionMenuOpen(ActionMenuOpenEvent event) {
        Item item = event.getItem() == null ? ItemFactory.getItemMatch(event.getItemStack()) : event.getItem();
        Player player = event.getWhoOpened();

        assert item != null;
        if(item.buyable && item.sellable)
            player.openInventory(new BuySellInventory().getMenu(item, player));
        else if(item.buyable)
            player.openInventory(new BuyInventory().getMenu(item, player));
        else if(item.sellable)
            player.openInventory(new SellInventory().getMenu(item, player));
    }

}
