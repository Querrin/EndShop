package com.hooklite.endshop.gui.item;

import com.hooklite.endshop.gui.ClickAction;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Item {
    void addClickAction(ClickAction action);

    List<ClickAction> getClickActions();

    ItemStack getItem();
}
