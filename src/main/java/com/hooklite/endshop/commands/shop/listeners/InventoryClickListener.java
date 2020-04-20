package com.hooklite.endshop.commands.shop.listeners;

import com.hooklite.endshop.commands.shop.*;
import com.hooklite.endshop.commands.shop.gui.Navigation;
import com.hooklite.endshop.commands.shop.gui.ShopsGui;
import com.hooklite.endshop.configuration.Configuration;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
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
        }


        for(Shop shop : Configuration.getShops()) {
            for(Inventory inventory : shop.getShopInventories()) {
                if(event.getInventory().getContents().equals(inventory.getContents())) {
                    ItemStack clickedItem = event.getCurrentItem();
                    Inventory clickedInventory = event.getClickedInventory();

                    if(clickedItem.equals(Navigation.getBackItem())) {
                        event.getWhoClicked().openInventory(shop.getShopInventories().get(0));
                        break;
                    }
                    if(clickedItem.equals(Navigation.getNextPageItem())) {
                        for(int i = 0; i < shop.getShopInventories().size(); i++) {
                            if(shop.getShopInventories().get(i).equals(clickedInventory)) {
                                if(shop.getShopInventories().size() > i) {
                                    event.getWhoClicked().openInventory(shop.getShopInventories().get(i + 1));
                                }
                                break;
                            }
                        }
                    }
                    if(clickedItem.equals(Navigation.getPreviousPageItem())) {
                        for(int i = 0; i < shop.getShopInventories().size(); i++) {
                            if(shop.getShopInventories().get(i).equals(clickedInventory)) {
                                if(i > 0) {
                                    event.getWhoClicked().openInventory(shop.getShopInventories().get(i - 1));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
