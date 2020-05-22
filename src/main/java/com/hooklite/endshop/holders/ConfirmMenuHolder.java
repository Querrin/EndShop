package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ConfirmMenuHolder implements InventoryHolder, PluginHolder {
    public final Action ACTION;
    public final Shop SHOP;
    public final Page PAGE;

    public ConfirmMenuHolder(PluginHolder holder, Action action, Inventory previousInventory) {
        SHOP = holder.getShop();
        PAGE = holder.getPage();
        ACTION = action;
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
}
