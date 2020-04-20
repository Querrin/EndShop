package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.*;
import com.hooklite.endshop.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory().equals(ShopGui.getInventory())) {
            if (event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
                for (Shop shop : Configuration.getShops()) {
                    if (shop.getSlot() == event.getSlot()) {
                        // TODO: Implement new inventory system and events
                        Player player = (Player) event.getWhoClicked();
                        player.openInventory(shop.getShopItemInventories().get(0));
                    }
                }
            }
            event.setCancelled(true);
        }
        if(ShopItemGui.getShopItemTitles().contains(event.getView().getTitle())) {
            int clicked = event.getSlot();
            List<Shop> items = Configuration.getShops();
            Inventory buySell = new BuySellMenu().getInventory();



            event.setCancelled(true);
        }
    }
}
