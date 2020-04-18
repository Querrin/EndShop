package com.hooklite.endshop.shop.listeners;

import com.hooklite.endshop.configuration.Configuration;
import com.hooklite.endshop.logging.MessageLogger;
import com.hooklite.endshop.shop.Shop;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // FIXME: clickedInventory also changes shopGuiInventory for some reason.
        Inventory clickedInventory = event.getClickedInventory();

        if (event.getClickedInventory().equals(Shop.getShopsGui())) {
            int clickedSlot = event.getSlot();
            MessageLogger.toConsole("A2");
            if (event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
                for (Shop shop : Configuration.getShops()) {
                    if (shop.getSlot() == clickedSlot) {
                        clickedInventory.clear();

                        for (int i = 0; i < shop.getShopItems().size(); i++) {
                            clickedInventory.setItem(Configuration.getShops().get(i).getSlot(), new ItemStack(
                                    shop.getShopItems().get(i).getItem(),
                                    1
                            ));
                        }
                        break;
                    }
                }
            }

        }
    }

}
