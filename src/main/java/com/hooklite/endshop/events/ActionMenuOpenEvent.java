package com.hooklite.endshop.events;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.holders.ItemMenuHolder;
import com.hooklite.endshop.holders.PluginHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ActionMenuOpenEvent extends Event implements OpenEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player PLAYER;
    private final ItemStack ITEM_STACK;
    private final PluginHolder PREVIOUS_HOLDER;
    private final Item ITEM;

    public ActionMenuOpenEvent(Player player, ItemStack item, PluginHolder holder) {
        PLAYER = player;
        PREVIOUS_HOLDER = holder;
        ITEM_STACK = item;
        ITEM = null;
    }

    public ActionMenuOpenEvent(Player player, Item item) {
        PLAYER = player;
        ITEM = item;
        ITEM_STACK = null;
        PREVIOUS_HOLDER = null;
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

    @Override
    public PluginHolder getPreviousHolder() {
        return PREVIOUS_HOLDER;
    }
}
