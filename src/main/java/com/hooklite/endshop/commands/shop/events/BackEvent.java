package com.hooklite.endshop.commands.shop.events;

import com.hooklite.endshop.commands.shop.Shop;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BackEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public BackEvent(Shop shop) {

    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
