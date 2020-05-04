package com.hooklite.endshop.config;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.requirements.ERequirement;
import com.hooklite.endshop.data.rewards.EReward;
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
    public static final NamespacedKey ITEM_KEY = new NamespacedKey(Configuration.getPlugin(), "item");
    public static final NamespacedKey SHOP_KEY = new NamespacedKey(Configuration.getPlugin(), "shop");
    public static final NamespacedKey PAGE_KEY = new NamespacedKey(Configuration.getPlugin(), "page");
    public static final NamespacedKey AMOUNT_KEY = new NamespacedKey(Configuration.getPlugin(), "amount");

    static {
        ItemStack backItem = new ItemStack(Material.CHEST, 1);
        ItemMeta backItemMeta = backItem.getItemMeta();
        Objects.requireNonNull(backItemMeta).setDisplayName(String.format("%s%sBack", ChatColor.RED, ChatColor.BOLD));
        backItem.setItemMeta(backItemMeta);

        ItemStack nextPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        Objects.requireNonNull(nextPageItemMeta).setDisplayName(String.format("%s%sNext page", ChatColor.GREEN, ChatColor.BOLD));
        nextPageItem.setItemMeta(nextPageItemMeta);

        ItemStack previousPageItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
        Objects.requireNonNull(previousPageItemMeta).setDisplayName(String.format("%s%sPrevious page", ChatColor.GREEN, ChatColor.BOLD));
        previousPageItem.setItemMeta(previousPageItemMeta);

        BACK_ITEM = backItem;
        NEXT_PAGE_ITEM = nextPageItem;
        PREVIOUS_PAGE_ITEM = previousPageItem;
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
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1);
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
        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta meta = item.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(String.format("%sBalance: %s$%.2f", ChatColor.GRAY, ChatColor.GREEN, Configuration.getEcon().getBalance(player)));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getBuyItem(Item eItem, int amount) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sBuy %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));

        return setItemLore(eItem.buyReq, amount, item, meta, eItem.buyReward);
    }

    public static ItemStack getSellItem(Item eItem, int amount) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sSell %sx%s%s", ChatColor.GREEN, ChatColor.BOLD, ChatColor.GRAY, ChatColor.GOLD, amount));

        return setItemLore(eItem.sellReq, amount, item, meta, eItem.sellReward);
    }

    public static ItemStack getSellMaxItem(Item eItem, Player player) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(String.format("%s%sSell MAX", ChatColor.GREEN, ChatColor.BOLD));

        return setItemLore(eItem.sellReq, eItem.sellReq.getMaxAmount(player), item, meta, eItem.sellReward);
    }

    private static ItemStack setItemLore(ERequirement requirement, int amount, ItemStack item, ItemMeta meta, EReward reward) {
        List<String> lore = new ArrayList<>();

        lore.add(String.format("%sRequired: %s%s", ChatColor.GRAY, ChatColor.RED, requirement.getName(amount)));
        lore.add(String.format("%sReward: %s%s", ChatColor.GRAY, ChatColor.GREEN, reward.getReward(amount)));

        assert meta != null;
        meta.getPersistentDataContainer().set(AMOUNT_KEY, PersistentDataType.INTEGER, amount);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
