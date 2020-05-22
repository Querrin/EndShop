package com.hooklite.endshop.data.items;

import com.hooklite.endshop.config.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class BalanceItem {
    private static final ItemStack BALANCE_ITEM = new ItemStack(Material.GOLD_NUGGET);
    private static final ItemMeta BALANCE_META = Objects.requireNonNull(BALANCE_ITEM.getItemMeta());

    public static ItemStack get(Player player) {
        BALANCE_META.setDisplayName(String.format("%sBalance: %s$%.2f", ChatColor.GRAY, ChatColor.GREEN, Configuration.getEcon().getBalance(player)));
        BALANCE_ITEM.setItemMeta(BALANCE_META);

        return BALANCE_ITEM;
    }
}
