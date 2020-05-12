package com.hooklite.endshop.listeners.inventory;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.events.*;
import com.hooklite.endshop.logging.MessageSender;
import com.hooklite.endshop.shop.BuySellMenu;
import com.hooklite.endshop.shop.ConfirmMenu;
import com.hooklite.endshop.shop.ItemMenu;
import com.hooklite.endshop.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getClickedInventory() != null && event.getCurrentItem() != null) {
            Inventory clickedInventory = event.getInventory();
            InventoryHolder holder = event.getInventory().getHolder();
            int clickedSlot = event.getSlot();
            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getCurrentItem();

            if(holder instanceof ShopMenu) {
                Bukkit.getPluginManager().callEvent(new ItemMenuOpenEvent(player, clickedSlot));
                event.setCancelled(true);
            }
            else if(holder instanceof ItemMenu) {
                ItemStack displayItem = clickedInventory.getItem(13);

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
                    Bukkit.getPluginManager().callEvent(new ActionMenuOpenEvent((Player) event.getWhoClicked(), item));
                }

                event.setCancelled(true);
            }
            else if(holder instanceof BuySellMenu) {
                ItemStack displayItem = clickedInventory.getItem(13);

                if(item.equals(MenuItemFactory.BACK_ITEM)) {
                    for(Shop shop : Configuration.getShops()) {
                        for(Page page : shop.pages) {
                            for(Item eItem : page.getItems()) {
                                if(eItem.displayItem.equals(clickedInventory.getItem(13))) {
                                    player.openInventory(page.getInventory());
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                }
                else if(item.getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                    if(item.getItemMeta().getDisplayName().contains("Max")) {
                        int amount = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuItemFactory.AMOUNT_KEY, PersistentDataType.INTEGER);

                        if(amount > 0)
                            Bukkit.getPluginManager().callEvent(new ConfirmMenuOpenEvent(player, displayItem, amount, Action.BUY));
                        else
                            MessageSender.toPlayer(player, "You do not meet the requirements!");
                    }
                    else {
                        Bukkit.getPluginManager().callEvent(new TransactionEvent(displayItem, event.getCurrentItem(), player, Action.BUY));
                    }
                }
                else if(item.getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    if(item.getItemMeta().getDisplayName().contains("Max")) {
                        int amount = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuItemFactory.AMOUNT_KEY, PersistentDataType.INTEGER);

                        if(amount > 0)
                            Bukkit.getPluginManager().callEvent(new ConfirmMenuOpenEvent(player, displayItem, amount, Action.SELL));
                        else
                            MessageSender.toPlayer(player, "You do not meet the requirements!");
                    }
                    else {
                        Bukkit.getPluginManager().callEvent(new TransactionEvent(displayItem, event.getCurrentItem(), player, Action.SELL));
                    }
                }

                event.setCancelled(true);
            }
            else if(holder instanceof ConfirmMenu) {
                if(event.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS)) {
                    Bukkit.getPluginManager().callEvent(new TransactionEvent(clickedInventory.getItem(22), event.getCurrentItem(), player, ((ConfirmMenu) holder).getAction()));
                    Bukkit.getPluginManager().callEvent(new ActionMenuOpenEvent(player, clickedInventory.getItem(22)));
                }
                else if(event.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS)) {
                    Bukkit.getPluginManager().callEvent(new ActionMenuOpenEvent(player, clickedInventory.getItem(22)));
                }

                event.setCancelled(true);
            }
        }
    }
}
