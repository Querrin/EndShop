package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ConfirmMenuHolder implements InventoryHolder, PluginHolder {
    public final Action ACTION;
    public final Inventory BACK_INVENTORY;
    public final Shop SHOP;
    public final Page PAGE;

    public ConfirmMenuHolder(Shop shop, Page page, Inventory backInventory, Action action) {
        SHOP = shop;
        PAGE = page;
        ACTION = action;
        BACK_INVENTORY = backInventory;
    }

    public ConfirmMenuHolder(PluginHolder holder, Inventory backInventory, Action action) {
        SHOP = holder.getShop();
        PAGE = holder.getPage();
        BACK_INVENTORY = backInventory;
        ACTION = action;
    }

    @Override
    public Inventory getInventory() {
        return BACK_INVENTORY;
    }

    @Override
    public Shop getShop() {
        return SHOP;
    }

    @Override
    public Page getPage() {
        return PAGE;
    }
}
