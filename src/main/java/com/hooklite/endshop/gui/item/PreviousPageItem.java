package com.hooklite.endshop.gui.item;

import com.hooklite.endshop.config.DefaultProvider;
import com.hooklite.endshop.config.LanguageProvider;
import com.hooklite.endshop.gui.ClickAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PreviousPageItem {
    private static final List<ClickAction> actions = new ArrayList<>();
    private static final ItemStack item;

    static {
        Material material = Material.matchMaterial(LanguageProvider.getConfig().getString("previous-page-material"));
        String title = DefaultProvider.getConfig().getString("previous-page-item");
        List<String> lore = DefaultProvider.getConfig().getStringList("previous-page-lore");

        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(title);
        meta.setLore(lore);

        item.setItemMeta(meta);
    }

    public static void addClickAction(ClickAction action) {
        actions.add(action);
    }

    public static List<ClickAction> getClickActions() {
        return actions;
    }

    public static ItemStack getItem() {
        return item;
    }
}
