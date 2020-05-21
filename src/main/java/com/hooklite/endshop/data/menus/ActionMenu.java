package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public interface ActionMenu {

    /**
     * Creates the shop menu for the specific player parsed.
     * @param item The item being bought/sold.
     * @param backInventory Inventory that will be opened upon pressing the back item.
     * @param player The player that does the transaction.
     * @return The inventory to open.
     */
    Inventory getMenu(Item item, Inventory backInventory, Player player);
}
