package com.hooklite.endshop.commands.shop.events;

import com.hooklite.endshop.commands.shop.ShopItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OpenBuySellMenuEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final HumanEntity OPENER;
    private final ShopItem CLICKED_ITEM;

    public OpenBuySellMenuEvent(HumanEntity opener, ShopItem item) {
        OPENER = opener;
        CLICKED_ITEM = item;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public HumanEntity getWhoOpened() {
        return OPENER;
    }

    public ShopItem getClickedItem() {
        return CLICKED_ITEM;
    }
}
