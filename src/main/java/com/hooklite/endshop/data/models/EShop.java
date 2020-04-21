package com.hooklite.endshop.data.models;

import org.bukkit.inventory.Inventory;

import java.util.Map;

public class EShop {
    public String title;
    public int slot;
    public Map<EPage, Inventory> pages;
    public ECurrency currency;
}
