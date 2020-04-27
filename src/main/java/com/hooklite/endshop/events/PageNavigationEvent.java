package com.hooklite.endshop.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class PageNavigationEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Inventory INVENTORY;
    private final Player PLAYER;
    private final PageNavigation DIRECTION;

    public PageNavigationEvent(Inventory inventory, Player player, PageNavigation direction) {
        INVENTORY = inventory;
        PLAYER = player;
        DIRECTION = direction;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Inventory getInventory() {
        return INVENTORY;
    }

    public Player getWhoClicked() {
        return PLAYER;
    }

    public PageNavigation getDirection() {
        return DIRECTION;
    }
}
