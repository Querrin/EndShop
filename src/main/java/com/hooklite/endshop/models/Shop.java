package com.hooklite.endshop.models;

import java.util.ArrayList;

public class Shop {
    private String name;
    private String title;
    private ArrayList<ShopItem> shopItems = new ArrayList<>();
    private int slot;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ArrayList<ShopItem> getShopItems() { return shopItems; }
    public void setShopItems(ArrayList<ShopItem> shopItems) { this.shopItems = shopItems; }
    public int getSlot() { return slot; }
    public void setSlot(int slot) { this.slot = slot; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
