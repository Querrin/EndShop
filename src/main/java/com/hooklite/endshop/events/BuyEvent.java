package com.hooklite.endshop.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class BuyEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ItemStack ITEM;
    private final Player PLAYER;
    private final int AMOUNT;

    public BuyEvent(ItemStack item, Player player, int amount) {
        ITEM = item;
        PLAYER = player;
        AMOUNT = amount;
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

    public int getAmount() {
        return AMOUNT;
    }
}
