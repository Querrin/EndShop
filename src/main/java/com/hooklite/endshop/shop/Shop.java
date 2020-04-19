package com.hooklite.endshop.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static Inventory shopsGui;

    private String name;
    private String title;
    private Material displayItem;
    private ArrayList<ShopItem> shopItems = new ArrayList<>();
    private int slot;
    private Inventory shopItemInventory;

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
    public Inventory getShopItemInventory() { return shopItemInventory; }
    public static Inventory getShopsGui() { return shopsGui; }

    public void initShopItemInventory() {
        Inventory inventory = Bukkit.createInventory(null, 36, title);
        for(ShopItem item : shopItems) {
            ItemStack itemStack = new ItemStack(new ItemStack(item.getItem(), 1));
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(item.getName());

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Buy: " + ChatColor.GREEN + "$" + item.getBuyPrice());
            lore.add(ChatColor.GRAY + "Sell: " + ChatColor.GREEN + "$" + item.getSellPrice());

            meta.setLore(lore);
            itemStack.setItemMeta(meta);

            inventory.addItem(itemStack);
        }
        shopItemInventory = inventory;
    }


}
