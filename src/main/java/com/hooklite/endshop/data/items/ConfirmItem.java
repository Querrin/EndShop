package com.hooklite.endshop.data.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

import static com.hooklite.endshop.config.MenuItemFactory.AMOUNT_KEY;

public class ConfirmItem {
    private static final ItemStack CONFIRM_ITEM = new ItemStack(Material.GREEN_STAINED_GLASS);
    private static final ItemMeta CONFIRM_META = Objects.requireNonNull(CONFIRM_ITEM.getItemMeta());

    public static ItemStack get(int amount) {
        CONFIRM_META.setDisplayName(String.format("%s%sCONFIRM", ChatColor.GREEN, ChatColor.BOLD));
        CONFIRM_META.getPersistentDataContainer().set(AMOUNT_KEY, PersistentDataType.INTEGER, amount);
        CONFIRM_ITEM.setItemMeta(CONFIRM_META);

        return CONFIRM_ITEM;
    }
}
