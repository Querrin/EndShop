package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ActionMenuHolder implements InventoryHolder, PluginHolder {
    public final Inventory BACK_INVENTORY;
    public final Shop SHOP;
    public final Page PAGE;

    @Override
    public Inventory getInventory() {
        return BACK_INVENTORY;
    }

    public ActionMenuHolder(Inventory backInventory, Shop shop, Page page) {
        BACK_INVENTORY = backInventory;
        SHOP = shop;
        PAGE = page;
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
