package com.hooklite.endshop.listeners.action;

import com.hooklite.endshop.config.ItemFactory;
import com.hooklite.endshop.data.menus.BuyConfirmInventory;
import com.hooklite.endshop.data.menus.SellConfirmInventory;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.events.ConfirmMenuOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ConfirmMenuOpenListener implements Listener {
    @EventHandler
    public void onConfirmMenuOpen(ConfirmMenuOpenEvent event) {
        if(event.getAction() == Action.BUY)
            event.getWhoOpened().openInventory(new BuyConfirmInventory().getMenu(ItemFactory.getItemMatch(event.getItem()), event.getAmount(), event.getAction(), event.getHolder().BACK_INVENTORY));
        else
            event.getWhoOpened().openInventory(new SellConfirmInventory().getMenu(ItemFactory.getItemMatch(event.getItem()), event.getAmount(), event.getAction(), event.getHolder().BACK_INVENTORY));
    }
}
