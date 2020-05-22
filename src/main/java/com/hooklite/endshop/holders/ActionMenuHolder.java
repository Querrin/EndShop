package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ActionMenuHolder implements InventoryHolder, PluginHolder {
    private final Shop SHOP;
    private final Page PAGE;
    private final Inventory PREVIOUS_INVENTORY;
    private final Item ITEM;

    @Override
    public Inventory getInventory() {
        return null;
    }

    public ActionMenuHolder(PluginHolder holder, Inventory previousInventory, Item item) {
        SHOP = holder.getShop();
        PAGE = holder.getPage();
        PREVIOUS_INVENTORY = previousInventory;
        ITEM = item;
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
}
