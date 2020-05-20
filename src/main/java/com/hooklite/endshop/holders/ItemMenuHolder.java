package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public class ItemMenuHolder implements InventoryHolder {
    public final Shop SHOP;
    public final Page PAGE;

    @Override
    public Inventory getInventory() {
        return null;
    }

    public ItemMenuHolder(Shop shop, Page page) {
        SHOP = shop;
        PAGE = page;
    }
}
