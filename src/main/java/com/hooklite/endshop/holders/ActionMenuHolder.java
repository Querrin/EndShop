package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ActionMenuHolder implements InventoryHolder, PluginHolder {
    private final Shop SHOP;
    private final Page PAGE;

    @Override
    public Inventory getInventory() {
        return null;
    }

    public ActionMenuHolder(PluginHolder holder, Inventory previousInventory) {
        SHOP = holder.getShop();
        PAGE = holder.getPage();
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
