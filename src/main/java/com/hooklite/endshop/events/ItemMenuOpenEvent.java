package com.hooklite.endshop.events;

import com.hooklite.endshop.holders.PluginHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ItemMenuOpenEvent extends Event implements OpenEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player PLAYER;
    private final int SLOT;
    private final PluginHolder PREVIOUS_HOLDER;

    public ItemMenuOpenEvent(Player player, int slot, PluginHolder previousHolder) {
        PLAYER = player;
        SLOT = slot;
        PREVIOUS_HOLDER = previousHolder;
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

    public int getSlot() {
        return SLOT;
    }

    @Override
    public PluginHolder getPreviousHolder() {
        return PREVIOUS_HOLDER;
    }
}