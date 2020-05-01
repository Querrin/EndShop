package com.hooklite.endshop.listeners.inventory;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.config.MenuLoader;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.events.*;
import com.hooklite.endshop.shop.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getClickedInventory() != null && event.getCurrentItem() != null) {
            int clickedSlot = event.getSlot();
            Player player = (Player) event.getWhoClicked();
            Inventory clickedInventory = event.getInventory();
            ItemStack item = event.getCurrentItem();

            if(clickedInventory.getHolder() instanceof ShopMenu) {
                Bukkit.getPluginManager().callEvent(new ItemMenuOpenEvent(player, clickedSlot));
                event.setCancelled(true);
            }

            if(clickedInventory.getHolder() instanceof ItemMenu) {

                if(item.equals(MenuLoader.getBackItem())) {
                    Bukkit.getPluginManager().callEvent(new ShopMenuOpenEvent(player));
                }
                else if(item.equals(MenuLoader.getNextPageItem())) {
                    Bukkit.getPluginManager().callEvent(new PageNavigationEvent(clickedInventory, player, PageNavigation.NEXT_PAGE));
                }
                else if(item.equals(MenuLoader.getPreviousPageItem())) {
                    Bukkit.getPluginManager().callEvent(new PageNavigationEvent(clickedInventory, player, PageNavigation.PREVIOUS_PAGE));
                }
                else if(!(item.getItemMeta().getDisplayName().contains("/")) && event.getClickedInventory() != player.getInventory()) {
                    Bukkit.getPluginManager().callEvent(new ActionMenuOpenEvent((Player) event.getWhoClicked(), item, clickedSlot));
                }

                event.setCancelled(true);
            }

            if(clickedInventory.getHolder() instanceof BuySellMenu || clickedInventory.getHolder() instanceof BuyMenu || clickedInventory.getHolder() instanceof SellMenu) {
                if(item.equals(MenuLoader.getBackItem())) {
                    for(EShop shop : Configuration.getShops()) {
                        for(EPage page : shop.pages) {
                            for(EItem eItem : page.getItems()) {
                                if(eItem.displayItem.equals(clickedInventory.getStorageContents()[13])) {
                                    player.openInventory(page.getInventory());
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                }
                else if(item.getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                    Bukkit.getPluginManager().callEvent(new BuyEvent(clickedInventory.getStorageContents()[13], event.getCurrentItem(), player));
                }
                else if(item.getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    Bukkit.getPluginManager().callEvent(new SellEvent(clickedInventory.getStorageContents()[13], event.getCurrentItem(), player));
                }

                event.setCancelled(true);
            }
        }
    }
}
