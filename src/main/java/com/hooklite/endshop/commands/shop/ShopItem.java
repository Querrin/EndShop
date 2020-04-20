package com.hooklite.endshop.commands.shop;

import org.bukkit.Material;

public class ShopItem {
    private String name;
    private Material item;
    private double buyPrice;
    private double sellPrice;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Material getItem() { return item; }
    public void setItem(Material item) { this.item = item; }
    public double getBuyPrice() { return buyPrice; }
    public void setBuyPrice(double buyPrice) { this.buyPrice = buyPrice; }
    public double getSellPrice() { return sellPrice; }
    public void setSellPrice(double sellPrice) { this.sellPrice = sellPrice; }
}
