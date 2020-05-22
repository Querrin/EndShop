package com.hooklite.endshop.listeners;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.config.InventoryFactory;
import com.hooklite.endshop.data.items.BackItem;
import com.hooklite.endshop.data.items.NextPageItem;
import com.hooklite.endshop.data.items.PreviousPageItem;
import com.hooklite.endshop.data.menus.*;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.events.*;
import com.hooklite.endshop.holders.*;
import com.hooklite.endshop.logging.MessageSender;
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
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if(holder instanceof ShopMenuHolder) {
                for(Shop shop : Configuration.getShops()) {
                    if(shop.slot == event.getSlot()) {
                        player.openInventory(shop.pages.get(0).getInventory());
                        break;
                    }
                }

                event.setCancelled(true);
            }
            else if(holder instanceof ItemMenuHolder) {
                if(clickedItem.equals(BackItem.get())) {
                    player.openInventory(InventoryFactory.getShopMenu(Configuration.getShops(), player));
                }
                else if(clickedItem.equals(NextPageItem.get())) {
                    Shop shop = ((ItemMenuHolder) holder).getShop();
                    int nextPage = ((ItemMenuHolder) holder).getPage().getNumber() + 1;

                    if(!(nextPage > shop.pages.size() - 1)) {
                        event.getWhoClicked().openInventory(shop.pages.get(nextPage).getInventory());
                    }
                }
                else if(clickedItem.equals(PreviousPageItem.get())) {
                    Shop shop = ((ItemMenuHolder) holder).getShop();
                    int previousPage = ((ItemMenuHolder) holder).getPage().getNumber() - 1;

                    if(!(previousPage < 0)) {
                        event.getWhoClicked().openInventory(shop.pages.get(previousPage).getInventory());
                    }
                }
                else if(!clickedItem.getItemMeta().getDisplayName().contains("/") && event.getClickedInventory() != player.getInventory()) {
                    Item item = findMatch(((ItemMenuHolder) holder).getPage(), clickedItem);

                    if(item.buyable && item.sellable)
                        player.openInventory(new BuySellInventory().get(item, (PluginHolder) holder, clickedInventory, player));
                    else if(item.buyable)
                        player.openInventory(new BuyInventory().get(item, (PluginHolder) holder, clickedInventory, player));
                    else if(item.sellable)
                        player.openInventory(new SellInventory().get(item, (PluginHolder) holder, clickedInventory, player));
                }

                event.setCancelled(true);
            }
            else if(holder instanceof ActionMenuHolder) {
                ItemStack displayItem = clickedInventory.getItem(13);

                if(clickedItem.equals(BackItem.get())) {
                    player.openInventory(((ActionMenuHolder) holder).getPreviousInventory());
                }
                else if(clickedItem.getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                    if(clickedItem.getItemMeta().getDisplayName().contains("Max")) {
                        int amount = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(Configuration.AMOUNT_KEY, PersistentDataType.INTEGER);

                        if(amount > 0) {
                            player.openInventory(new BuyConfirmInventory().getMenu(((ActionMenuHolder) holder).getItem(), amount, Action.BUY, clickedInventory, (PluginHolder) holder));
                        }
                        else
                            MessageSender.toPlayer(player, "You do not meet the requirements!");
                    }
                    else {
                        Bukkit.getPluginManager().callEvent(new TransactionEvent(displayItem, event.getCurrentItem(), player, Action.BUY));
                    }

                    player.openInventory(updateActionInventory(clickedInventory, ((ActionMenuHolder) holder).getItem(), player));
                }
                else if(clickedItem.getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    if(clickedItem.getItemMeta().getDisplayName().contains("Max")) {
                        int amount = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(Configuration.AMOUNT_KEY, PersistentDataType.INTEGER);

                        if(amount > 0)
                            player.openInventory(new SellConfirmInventory().getMenu(((ActionMenuHolder) holder).getItem(), amount, Action.SELL, clickedInventory, (PluginHolder) holder));
                        else
                            MessageSender.toPlayer(player, "You do not meet the requirements!");
                    }
                    else {
                        Bukkit.getPluginManager().callEvent(new TransactionEvent(displayItem, event.getCurrentItem(), player, Action.SELL));
                    }

                    player.openInventory(updateActionInventory(clickedInventory, ((ActionMenuHolder) holder).getItem(), player));
                }

                event.setCancelled(true);
            }
            else if(holder instanceof ConfirmMenuHolder) {
                if(event.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS)) {
                    Bukkit.getPluginManager().callEvent(new TransactionEvent(clickedInventory.getItem(22), event.getCurrentItem(), player, ((ConfirmMenuHolder) holder).getAction()));
                    player.openInventory(((ConfirmMenuHolder) holder).getPreviousInventory());
                }
                else if(event.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS)) {
                    player.openInventory(((ConfirmMenuHolder) holder).getPreviousInventory());
                }

                event.setCancelled(true);
            }
        }
    }

    private Item findMatch(Page page, ItemStack itemStack) {
        for(Item item : page.getItems()) {
            if(item.displayItem == itemStack)
                return item;
        }

        throw new IllegalArgumentException("Invalid inventory argument");
    }

    private Inventory updateActionInventory(Inventory currentInventory, Item item, Player player) {
        if(item.buyable && item.sellable)
            BuySellInventory.update(currentInventory, item, player);
        else if(item.buyable)
            BuyInventory.update(currentInventory, item, player);
        else if(item.sellable)
            SellInventory.update(currentInventory, item, player);

        return currentInventory;
    }
}
