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

public class BuyItem {

    public static ItemStack get(Item item, int amount) {
        ItemStack buyItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = buyItem.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sBuy %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));
        meta.setLore(getLore(item, amount));

        buyItem.setItemMeta(meta);

        return buyItem;
    }

    public static ItemStack getMax(Item item, Player player) {
        ItemStack buyItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = buyItem.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.format("%s%sBuy Max", ChatColor.GREEN, ChatColor.BOLD));
        meta.setLore(getLore(item, item.buyReq.getMaxAmount(item, player, Action.BUY)));

        buyItem.setItemMeta(meta);

        return buyItem;
    }

    private static List<String> getLore(Item item, int amount) {
        List<String> lore = new ArrayList<>();

        if(amount == 0) {
            lore.add(String.format("%sYou do not have the requirements!", ChatColor.RED));
        }
        else {
            lore.add(String.format("%sRequired: %s%s", ChatColor.GRAY, ChatColor.RED, item.buyReq.getName(amount)));
            lore.add(String.format("%sReward: %s%s", ChatColor.GRAY, ChatColor.GREEN, item.buyReward.getReward(amount)));
        }

        return lore;
    }
}
