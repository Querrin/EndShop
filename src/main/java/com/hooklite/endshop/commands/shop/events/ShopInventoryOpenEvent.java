package com.hooklite.endshop.commands.shop.events;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;


public class ShopInventoryOpenEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final HumanEntity OPENER;
    private final Inventory INVENTORY;

    public ShopInventoryOpenEvent(HumanEntity opener, Inventory inventory) {
        OPENER = opener;
        INVENTORY = inventory;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HumanEntity getWhoOpened() {
        return OPENER;
    }

    public Inventory getInventory() {
        return INVENTORY;
    }
}
