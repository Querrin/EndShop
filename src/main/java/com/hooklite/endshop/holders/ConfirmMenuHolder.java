package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ConfirmMenuHolder implements InventoryHolder, PluginHolder {
    private final Action ACTION;
    private final Shop SHOP;
    private final Page PAGE;
    private final Inventory PREVIOUS_INVENTORY;
    private final Item ITEM;

    public ConfirmMenuHolder(PluginHolder holder, Action action, Inventory previousInventory, Item item) {
        SHOP = holder.getShop();
        PAGE = holder.getPage();
        ACTION = action;
        PREVIOUS_INVENTORY = previousInventory;
        ITEM = item;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    @Override
    public Shop getShop() {
        return SHOP;
    }

    @Override
    public Page getPage() {
        return PAGE;
    }

    @Override
    public Inventory getPreviousInventory() {
        return PREVIOUS_INVENTORY;
    }

    public Item getItem() {
        return ITEM;
    }

    public Action getAction() {
        return ACTION;
    }
}
