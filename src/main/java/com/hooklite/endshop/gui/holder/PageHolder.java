package com.hooklite.endshop.gui.holder;

import com.hooklite.endshop.gui.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class PageHolder implements InventoryHolder {
    private final int size;
    private final String title;
    private final Map<Integer, Item> items = new HashMap<>();

    public PageHolder(int size, String title) {
        this.size = size;
        this.title = title;
    }

    public PageHolder(int size) {
        this.size = size;
        title = "Menu";
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, size, title);

        for(Map.Entry<Integer, Item> entry : items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItem());
        }

        return inventory;
    }

    public void setItem(int slot, Item item) {
        items.put(slot, item);
    }
}
