package com.hooklite.endshop.events;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ActionMenuOpenEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player PLAYER;
    private final ItemStack ITEM_STACK;
    private final Item ITEM;

    public ActionMenuOpenEvent(Player player, ItemStack item) {
        PLAYER = player;
        ITEM_STACK = item;
        ITEM = null;
    }

    public ActionMenuOpenEvent(Player player, Item item) {
        PLAYER = player;
        ITEM_STACK = null;
        ITEM = item;
    }


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

    public Item getItem() {
        return ITEM;
    }
}
