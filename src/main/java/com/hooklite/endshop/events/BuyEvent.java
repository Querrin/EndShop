package com.hooklite.endshop.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class BuyEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ItemStack ITEM;
    private final ItemStack CLICKED_ITEM;
    private final Player PLAYER;

    public BuyEvent(ItemStack rewardItem, ItemStack clickedItem, Player player) {
        ITEM = rewardItem;
        CLICKED_ITEM = clickedItem;
        PLAYER = player;
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

    public ItemStack getClickedItem() {
        return CLICKED_ITEM;
    }
}
