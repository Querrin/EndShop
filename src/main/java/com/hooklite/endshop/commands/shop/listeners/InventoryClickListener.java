package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.*;
import com.hooklite.endshop.commands.shop.gui.Navigation;
import com.hooklite.endshop.commands.shop.gui.ShopsGui;
import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.security.MessageDigest;


public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            if (event.getClickedInventory().equals(ShopsGui.getInventory())) {
                if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getClickedInventory() != null) {
                    if (event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
                        for (Shop shop : Configuration.getShops()) {
                            if (shop.getSlot() == event.getSlot()) {
                                event.getWhoClicked().openInventory(shop.getShopInventories().get(0));
                            }
                        }
                    }
                }
                event.setCancelled(true);
            } else {
                for (Shop shop : Configuration.getShops()) {
                    for (Inventory inventory : shop.getShopInventories()) {
                        if (event.getClickedInventory().equals(inventory)) {
                            if (event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
                                ItemStack clickedItem = event.getCurrentItem();
                                Inventory clickedInventory = event.getClickedInventory();

                                if (event.getCurrentItem() != null) {
                                    if (clickedItem.equals(Navigation.getBackItem())) {
                                        event.getWhoClicked().openInventory(ShopsGui.getInventory());
                                        event.setCancelled(true);
                                        break;
                                    }
                                    if (clickedItem.equals(Navigation.getNextPageItem())) {
                                        for (int i = 0; i < shop.getShopInventories().size(); i++) {
                                            if (shop.getShopInventories().get(i).equals(clickedInventory)) {
                                                if (shop.getShopInventories().size() > i) {
                                                    event.getWhoClicked().openInventory(shop.getShopInventories().get(i + 1));
                                                }
                                                event.setCancelled(true);
                                                break;
                                            }
                                        }
                                    }
                                    if (clickedItem.equals(Navigation.getPreviousPageItem())) {
                                        for (int i = 0; i < shop.getShopInventories().size(); i++) {
                                            if (shop.getShopInventories().get(i).equals(clickedInventory)) {
                                                if (i > 0) {
                                                    event.getWhoClicked().openInventory(shop.getShopInventories().get(i - 1));
                                                }
                                                event.setCancelled(true);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    event.setCancelled(true);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
