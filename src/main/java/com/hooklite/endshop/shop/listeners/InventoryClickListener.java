package com.hooklite.endshop.shop.listeners;

import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.shop.Shop;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory().equals(Shop.getShopsGui())) {
            if (event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
                for (Shop shop : Configuration.getShops()) {
                    if (shop.getSlot() == event.getSlot()) {
                        event.getWhoClicked().openInventory(shop.getShopItemInventories());
                    }
                }
            }
            event.setCancelled(true);
        }

    }

}
