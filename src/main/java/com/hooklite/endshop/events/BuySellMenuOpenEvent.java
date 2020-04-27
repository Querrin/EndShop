package com.hooklite.endshop.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BuySellMenuOpenEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player PLAYER;
    private final int SLOT;

    public BuySellMenuOpenEvent(Player player, int slot) {
        PLAYER = player;
        SLOT = slot;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getWhoOpened() {
        return PLAYER;
    }

    public int getSlot() {
        return SLOT;
    }
}
