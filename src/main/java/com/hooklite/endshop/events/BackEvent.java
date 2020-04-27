package com.hooklite.endshop.events;

import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BackEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final EShop SHOP;
    private final EPage PAGE;
    private final Player PLAYER;

    public BackEvent(EShop shop, EPage page, Player player) {
        SHOP = shop;
        PAGE = page;
        PLAYER = player;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getWhoClicked() {
        return PLAYER;
    }

    public EShop getShop() {
        return SHOP;
    }

    public EPage getPage() {
        return PAGE;
    }
}
