package com.hooklite.endshop.commands.shop;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static Inventory shopsGui;

    private String name;
    private String title;
    private Material displayItem;
    private ArrayList<ShopItem> shopItems = new ArrayList<>();
    private int slot;
    private List<Inventory> shopInventories;
    private List<Inventory> buySellInventories;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ArrayList<ShopItem> getShopItems() { return shopItems; }
    public void setShopItems(ArrayList<ShopItem> shopItems) { this.shopItems = shopItems; }
    public void addShopItem(ShopItem item) { shopItems.add(item); }
    public int getSlot() { return slot; }
    public void setSlot(int slot) { this.slot = slot; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Material getDisplayItem() { return displayItem; }
    public void setDisplayItem(Material displayItem) { this.displayItem = displayItem; }
    public List<Inventory> getShopInventories() { return shopInventories; }
    public static Inventory getShopsGui() { return shopsGui; }
    public void setShopInventories(List<Inventory> shopInventories) { this.shopInventories = shopInventories; }
    public List<Inventory> getBuySellInventories() { return buySellInventories; }
    public void setBuySellInventories(List<Inventory> buySellInventories) { this.buySellInventories = buySellInventories; }
}
