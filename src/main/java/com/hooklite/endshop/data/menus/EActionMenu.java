package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface EActionMenu {
    Inventory getMenu(Item item, Player player);
}
