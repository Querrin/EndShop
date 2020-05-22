package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import org.bukkit.inventory.Inventory;

public interface PluginHolder {
    Shop getShop();
    Page getPage();
    Inventory getPreviousInventory();
}
