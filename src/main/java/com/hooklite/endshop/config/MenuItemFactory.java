package com.hooklite.endshop.config;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.requirements.Requirement;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.data.rewards.Reward;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuItemFactory {

    public static final ItemStack BACK_ITEM;
    public static final ItemStack NEXT_PAGE_ITEM;
    public static final ItemStack PREVIOUS_PAGE_ITEM;
    public static final ItemStack CANCEL_ITEM;
    public static final NamespacedKey AMOUNT_KEY = new NamespacedKey(Configuration.getPlugin(), "amount");

    static {
        ItemStack backItem = new ItemStack(Material.CHEST);
        ItemMeta backItemMeta = backItem.getItemMeta();
        Objects.requireNonNull(backItemMeta).setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
        backItem.setItemMeta(backItemMeta);

        ItemStack nextPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        Objects.requireNonNull(nextPageItemMeta).setDisplayName(String.format("%s%sNext page", ChatColor.GREEN, ChatColor.BOLD));
        nextPageItem.setItemMeta(nextPageItemMeta);

        ItemStack previousPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
        Objects.requireNonNull(previousPageItemMeta).setDisplayName(String.format("%s%sPrevious page", ChatColor.GREEN, ChatColor.BOLD));
        previousPageItem.setItemMeta(previousPageItemMeta);

        ItemStack cancelItem = new ItemStack(Material.RED_STAINED_GLASS);
        ItemMeta cancelItemMeta = cancelItem.getItemMeta();
        cancelItemMeta.setDisplayName(String.format("%s%sCANCEL", ChatColor.RED, ChatColor.BOLD));
        cancelItem.setItemMeta(cancelItemMeta);

        BACK_ITEM = backItem;
        NEXT_PAGE_ITEM = nextPageItem;
        PREVIOUS_PAGE_ITEM = previousPageItem;
        CANCEL_ITEM = cancelItem;
    }

    public static ItemStack getConfirmItem(int amount) {
        ItemStack confirmItem = new ItemStack(Material.GREEN_STAINED_GLASS);
        ItemMeta confirmItemMeta = confirmItem.getItemMeta();
        confirmItemMeta.setDisplayName(String.format("%s%sCONFIRM", ChatColor.GREEN, ChatColor.BOLD));
        confirmItemMeta.getPersistentDataContainer().set(AMOUNT_KEY, PersistentDataType.INTEGER, amount);
        confirmItem.setItemMeta(confirmItemMeta);

        return confirmItem;
    }

    static ItemStack getPageNumberItem(int page, int pages) {
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(String.valueOf(ChatColor.RED) +
                (page + 1) +
                ChatColor.DARK_GRAY +
                "/" +
                ChatColor.RED +
                pages);

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack getPageNumberItem(Shop shop, int number) {
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(String.valueOf(ChatColor.RED) +
                (number + 1) +
                ChatColor.DARK_GRAY +
                "/" +
                ChatColor.RED +
                shop.pages.size());

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getBalanceItem(Player player) {
        ItemStack item = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = item.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(String.format("%sBalance: %s$%.2f", ChatColor.GRAY, ChatColor.GREEN, Configuration.getEcon().getBalance(player)));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getBuyItem(Item eItem, int amount) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sBuy %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));

        return setItemLore(eItem.buyReq, amount, item, meta, eItem.buyReward);
    }

    public static ItemStack getSellItem(Item eItem, int amount) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sSell %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));

        return setItemLore(eItem.sellReq, amount, item, meta, eItem.sellReward);
    }

    public static ItemStack getSellMaxItem(Item eItem, Player player) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sSell Max", ChatColor.GREEN, ChatColor.BOLD));

        return setItemLore(eItem.sellReq, eItem.sellReq.getMaxAmount(eItem, player, Action.SELL), item, meta, eItem.sellReward);
    }

    public static ItemStack getBuyMaxItem(Item eItem, Player player) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sBuy Max", ChatColor.GREEN, ChatColor.BOLD));

        return setItemLore(eItem.buyReq, eItem.buyReq.getMaxAmount(eItem, player, Action.BUY), item, meta, eItem.buyReward);
    }

    private static ItemStack setItemLore(Requirement requirement, int amount, ItemStack item, ItemMeta meta, Reward reward) {
        List<String> lore = new ArrayList<>();

        if(amount == 0) {
            lore.add(String.format("%sYou do not have the requirements!", ChatColor.RED));
        }
        else {
            lore.add(String.format("%sRequired: %s%s", ChatColor.GRAY, ChatColor.RED, requirement.getName(amount)));
            lore.add(String.format("%sReward: %s%s", ChatColor.GRAY, ChatColor.GREEN, reward.getReward(amount)));
        }

        assert meta != null;
        meta.getPersistentDataContainer().set(AMOUNT_KEY, PersistentDataType.INTEGER, amount);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
