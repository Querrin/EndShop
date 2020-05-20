package com.hooklite.endshop.events;

import com.hooklite.endshop.holders.ItemMenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PageNavigationEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ItemMenuHolder HOLDER;
    private final Player PLAYER;
    private final PageNavigation DIRECTION;

    public PageNavigationEvent(ItemMenuHolder holder, Player player, PageNavigation direction) {
        HOLDER = holder;
        PLAYER = player;
        DIRECTION = direction;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getWhoClicked() {
        return PLAYER;
    }

    public PageNavigation getDirection() {
        return DIRECTION;
    }

    public ItemMenuHolder getHolder() {
        return HOLDER;
    }
}
