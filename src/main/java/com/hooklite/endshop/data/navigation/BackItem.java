package com.hooklite.endshop.data.navigation;

import org.bukkit.inventory.Inventory;

public class BackItem {
    public final Inventory PREVIOUS_INVENTORY;

    public BackItem(Inventory previousInventory) {
        PREVIOUS_INVENTORY = previousInventory;
    }
}
