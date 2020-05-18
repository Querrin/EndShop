package com.hooklite.endshop.shop;

import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ConfirmMenuHolder implements InventoryHolder {
    private final Action ACTION;

    public ConfirmMenuHolder(Action action) {
        ACTION = action;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    public Action getAction() {
        return ACTION;
    }
}
