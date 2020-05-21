package com.hooklite.endshop.events;

import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.holders.ActionMenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ConfirmMenuOpenEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player PLAYER;
    private final ItemStack ITEM;
    private final int AMOUNT;
    private final Action ACTION;
    private final ActionMenuHolder HOLDER;

    public ConfirmMenuOpenEvent(Player player, ItemStack displayItem, int amount, Action action, ActionMenuHolder holder) {
        PLAYER = player;
        ITEM = displayItem;
        AMOUNT = amount;
        ACTION = action;
        HOLDER = holder;
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

    public ItemStack getItem() {
        return ITEM;
    }

    public int getAmount() {
        return AMOUNT;
    }

    public Action getAction() {
        return ACTION;
    }

    public ActionMenuHolder getHolder() {
        return HOLDER;
    }
}
