package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.*;
import com.hooklite.endshop.commands.shop.events.BackEvent;
import com.hooklite.endshop.commands.shop.events.OpenBuySellMenuEvent;
import com.hooklite.endshop.commands.shop.events.OpenShopEvent;
import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;



public class InventoryClickListener implements Listener {
    public OpenShopEvent openShopEvent;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory().equals(ShopGui.getInventory())) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getClickedInventory() != null) {
                if (event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
                    for (Shop shop : Configuration.getShops()) {
                        if (shop.getSlot() == event.getSlot()) {
                            // TODO: Implement new inventory system and events
                            openShopEvent = new OpenShopEvent(event.getWhoClicked(), shop);
                            Bukkit.getPluginManager().callEvent(openShopEvent);
                        }
                    }
                }
            }
            event.setCancelled(true);
        }

        // Checks if the shop inventory's title is equal to clicked one
        else if (ShopItemGui.getShopItemTitles().contains(event.getView().getTitle())) {
            Bukkit.getPluginManager().callEvent(new BackEvent(openShopEvent.getShop()));

            if (event.getCurrentItem().equals(Navigation.getBackItem())) {
                event.getWhoClicked().openInventory(ShopGui.getInventory());
            }

            try {
                for (ShopItem item : ShopItemGui.getAllShopItems()) {
                    if (item.getName().equals(event.getCurrentItem().getItemMeta().getDisplayName())) {

                    }
                }
            } catch (NullPointerException e) {
                MessageLogger.toConsole("Could not check item meta!");
                e.printStackTrace();
            }

            for (ShopItem item : openShopEvent.getShop().getShopItems()) {
                if (item.getName().equals(event.getCurrentItem().getItemMeta().getDisplayName())) {
                    Bukkit.getPluginManager().callEvent(new OpenBuySellMenuEvent(event.getWhoClicked(), item));
                }
            }
            event.setCancelled(true);
        }
    }
}
