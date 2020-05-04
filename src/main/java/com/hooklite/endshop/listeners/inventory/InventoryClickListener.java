package com.hooklite.endshop.listeners.inventory;

import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.persistance.PageTagType;
import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.events.*;
import com.hooklite.endshop.shop.BuySellMenu;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
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
            ItemStack displayItem = clickedInventory.getItem(13);

            if(clickedInventory.getHolder() instanceof ShopMenu) {
                Bukkit.getPluginManager().callEvent(new ItemMenuOpenEvent(player, clickedSlot));
                event.setCancelled(true);
            }

            if(clickedInventory.getHolder() instanceof ItemMenu) {

                if(item.equals(MenuItemFactory.BACK_ITEM)) {
                    Bukkit.getPluginManager().callEvent(new ShopMenuOpenEvent(player));
                }
                else if(item.equals(MenuItemFactory.NEXT_PAGE_ITEM)) {
                    Bukkit.getPluginManager().callEvent(new PageNavigationEvent(displayItem, player, PageNavigation.NEXT_PAGE));
                }
                else if(item.equals(MenuItemFactory.PREVIOUS_PAGE_ITEM)) {
                    Bukkit.getPluginManager().callEvent(new PageNavigationEvent(displayItem, player, PageNavigation.PREVIOUS_PAGE));
                }
                else if(!(item.getItemMeta().getDisplayName().contains("/")) && event.getClickedInventory() != player.getInventory()) {
                    Bukkit.getPluginManager().callEvent(new ActionMenuOpenEvent((Player) event.getWhoClicked(), item, clickedSlot));
                }

                event.setCancelled(true);
            }

            if(clickedInventory.getHolder() instanceof BuySellMenu) {
                if(item.equals(MenuItemFactory.BACK_ITEM)) {
                    Page page = displayItem.getItemMeta().getPersistentDataContainer().get(MenuItemFactory.PAGE_KEY, PageTagType.getInstance());

                    player.openInventory(page.getInventory());
                    event.setCancelled(true);
                }
                else if(item.getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                    Bukkit.getPluginManager().callEvent(new TransactionEvent(displayItem, event.getCurrentItem(), player, EAction.BUY));
                }
                else if(item.getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    Bukkit.getPluginManager().callEvent(new TransactionEvent(displayItem, event.getCurrentItem(), player, EAction.SELL));
                }

                event.setCancelled(true);
            }
        }
    }
}
