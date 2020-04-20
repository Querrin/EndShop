package com.hooklite.endshop.commands.shop.events;

import com.hooklite.endshop.commands.shop.Shop;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OpenShopEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Shop OPENED_SHOP;
    private final HumanEntity OPENER;

    public OpenShopEvent(HumanEntity opener, Shop shop) {
        OPENED_SHOP = shop;
        OPENER = opener;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Shop getShop() {
        return OPENED_SHOP;
    }

    public HumanEntity getWhoOpened() {
        return OPENER;
    }

}
