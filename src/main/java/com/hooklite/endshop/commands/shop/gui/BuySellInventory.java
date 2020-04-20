package com.hooklite.endshop.commands.shop.gui;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class BuySellInventory implements Inventory {
    private ItemStack[] items;
    private int maxStackSize = 64;
    private InventoryHolder holder;
    private int size;
    private String title;

    public BuySellInventory(InventoryHolder holder, int size, String title) {
        if(size <= 54 && size >= 9 && size % 9 == 0) {
            this.items = new ItemStack[size];
            this.holder = holder;
            this.size = size;
            this.title = title;
        }
        else
            throw new IllegalArgumentException("Inventory size must be 9-45 and dividable by 9!");
    }

    @Override
    public int getSize() {
        return items.length;
    }

    @Override
    public int getMaxStackSize() {
        return maxStackSize;
    }

    @Override
    public void setMaxStackSize(int size) {
        maxStackSize = size;
    }

    @Override
    public ItemStack getItem(int index) {
        return items[index];
    }

    @Override
    public void setItem(int index, ItemStack item) {
        items[index] = item;
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        return null;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ItemStack[] getContents() {
        return new ItemStack[0];
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {

    }

    @Override
    public ItemStack[] getStorageContents() {
        return items;

    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        this.items = items;
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        for(ItemStack item : items) {
            if(item.getType().equals(material))
                return true;
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack item) {
        for(ItemStack itemStack : items) {
            if(itemStack.equals(item))
                return true;
        }
        return false;
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        for(ItemStack item : items) {
            if(item.getType().equals(material))
                if(item.getAmount() == amount)
                    return true;
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        for(ItemStack itemStack : items) {
            if(itemStack.equals(item))
                if(itemStack.getAmount() == amount)
                    return true;
        }
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        for(ItemStack itemStack : items) {
            if(itemStack.equals(item))
                if(itemStack.getAmount() >= amount)
                    return true;
        }
        return false;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        return null;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public int first(ItemStack item) {
        return 0;
    }

    @Override
    public int firstEmpty() {
        return 0;
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        for(int i = 0; i < items.length; i++) {
            if(items[i].getType().equals(material)) {
                items[i] = null;
            }
        }
    }

    @Override
    public void remove(ItemStack item) {
        for(ItemStack itemStack : items) {
            if(itemStack.equals(item))
                itemStack = null;
        }
    }

    @Override
    public void clear(int index) {
        items[index] = null;
    }

    @Override
    public void clear() {
        items = new ItemStack[size];
    }

    @Override
    public List<HumanEntity> getViewers() {
        return null;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CHEST;
    }

    @Override
    public InventoryHolder getHolder() {
        return holder;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }
}
