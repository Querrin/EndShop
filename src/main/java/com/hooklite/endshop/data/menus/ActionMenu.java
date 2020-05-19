package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface ActionMenu {

    /**
     * Creates the shop menu for the specific player parsed.
     * @param item The item being bought/sold.
     * @param player The player that does the transaction.
     * @return The inventory to open.
     */
    Inventory getMenu(Item item, Player player);
}
