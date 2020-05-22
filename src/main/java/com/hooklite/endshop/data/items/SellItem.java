package com.hooklite.endshop.data.items;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SellItem {
    public static ItemStack get(Item item, int amount) {
        ItemStack sellItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = sellItem.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sSell %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));
        meta.setLore(getLore(item, amount));

        sellItem.setItemMeta(meta);

        return sellItem;
    }

    public static ItemStack getMax(Item item, Player player) {
        ItemStack sellItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = sellItem.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sSell Max", ChatColor.GREEN, ChatColor.BOLD));
        meta.setLore(getLore(item, item.sellReq.getMaxAmount(item, player, Action.SELL)));

        sellItem.setItemMeta(meta);

        return sellItem;
    }

    private static List<String> getLore(Item item, int amount) {
        List<String> lore = new ArrayList<>();

        if(amount == 0) {
            lore.add(String.format("%sYou do not have the requirements!", ChatColor.RED));
        }
        else {
            lore.add(String.format("%sRequired: %s%s", ChatColor.GRAY, ChatColor.RED, item.sellReq.getName(amount)));
            lore.add(String.format("%sReward: %s%s", ChatColor.GRAY, ChatColor.GREEN, item.sellReward.getReward(amount)));
        }

        return lore;
    }
}
