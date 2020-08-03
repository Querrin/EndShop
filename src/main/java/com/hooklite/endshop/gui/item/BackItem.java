package com.hooklite.endshop.gui.item;

import com.hooklite.endshop.config.DefaultProvider;
import com.hooklite.endshop.config.LanguageProvider;
import com.hooklite.endshop.gui.ClickAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BackItem {
    private static final List<ClickAction> actions = new ArrayList<>();
    private static final ItemStack item;

    static {
        Material material = Material.matchMaterial(LanguageProvider.getConfig().getString("back-material"));
        String title = DefaultProvider.getConfig().getString("back-item");
        List<String> lore = DefaultProvider.getConfig().getStringList("back-lore");

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
