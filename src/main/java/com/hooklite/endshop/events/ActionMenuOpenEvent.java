package com.hooklite.endshop.events;

import com.hooklite.endshop.data.menus.ActionMenu;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.holders.ActionMenuHolder;
import com.hooklite.endshop.holders.ItemMenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ActionMenuOpenEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player PLAYER;
    private final ItemStack ITEM_STACK;
    private final ItemMenuHolder HOLDER;

    public ActionMenuOpenEvent(Player player, ItemStack item, ItemMenuHolder holder) {
        PLAYER = player;
        HOLDER = holder;
        ITEM_STACK = item;
    }

    public ActionMenu(Player player, Item item)

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getWhoOpened() {
        return PLAYER;
    }

    public ItemStack getItemStack() {
        return ITEM_STACK;
    }

    public ItemMenuHolder getInventoryHolder() {
        return HOLDER;
    }
}
