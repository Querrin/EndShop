package com.hooklite.endshop.listeners.inventory;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.config.InventoryLoader;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.*;
import com.hooklite.endshop.shop.BuySellMenu;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getCurrentItem() != null) {
            int clickedSlot = event.getSlot();
            Player player = (Player) event.getWhoClicked();
            Inventory clickedInventory = event.getInventory();

            if (clickedInventory.getHolder() instanceof ShopMenu) {
                Bukkit.getPluginManager().callEvent(new ItemMenuOpenEvent(player, clickedSlot));
                event.setCancelled(true);
            }

            if (clickedInventory.getHolder() instanceof ItemMenu) {
                ItemStack item = event.getCurrentItem();

                if (item.equals(InventoryLoader.getBackItem())) {
                    Bukkit.getPluginManager().callEvent(new ShopMenuOpenEvent(player));
                } else if (item.equals(InventoryLoader.getNextPageItem())) {
                    // TODO: Optimize unnecessary looping
                    for (EShop shop : Configuration.getShops()) {
                        for (EPage page : shop.pages) {
                            if (clickedInventory.equals(page.getInventory())) {
                                Bukkit.getPluginManager().callEvent(new NextPageEvent(shop, page, player));
                            }
                            break;
                        }
                    }
                } else if (item.equals(InventoryLoader.getPreviousPageItem())) {
                    // TODO: Optimize unnecessary looping
                    for (EShop shop : Configuration.getShops()) {
                        for (EPage page : shop.pages) {
                            if (clickedInventory.equals(page.getInventory())) {
                                Bukkit.getPluginManager().callEvent(new PreviousPageEvent(shop, page, player));
                            }

                            break;
                        }
                    }
                } else {
                    Bukkit.getPluginManager().callEvent(new BuySellMenuOpenEvent((Player) event.getWhoClicked(), clickedSlot));
                }

                event.setCancelled(true);
            }

            if (clickedInventory.getHolder() instanceof BuySellMenu) {

            }
        }
    }
}
