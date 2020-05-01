package com.hooklite.endshop.data.menus;

import com.hooklite.endshop.data.models.EItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface EActionMenu {
    Inventory getMenu(EItem item, Player player);
}
