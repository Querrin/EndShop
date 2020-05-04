package com.hooklite.endshop.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PageNavigationEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ItemStack ITEM;
    private final Player PLAYER;
    private final PageNavigation DIRECTION;

    public PageNavigationEvent(ItemStack item, Player player, PageNavigation direction) {
        ITEM = item;
        PLAYER = player;
        DIRECTION = direction;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public ItemStack getItem() {
        return ITEM;
    }

    public Player getWhoClicked() {
        return PLAYER;
    }

    public PageNavigation getDirection() {
        return DIRECTION;
    }
}
