package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ItemMenuHolder implements InventoryHolder, PluginHolder {
    public final Shop SHOP;
    public final Page PAGE;

    @Override
    public Inventory getInventory() {
        return null;
    }

    public ItemMenuHolder(PluginHolder holder) {
        SHOP = holder.getShop();
        PAGE = holder.getPage();
    }

    public ItemMenuHolder(Shop shop, Page page) {
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
