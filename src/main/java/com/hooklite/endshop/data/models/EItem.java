package com.hooklite.endshop.data.models;

import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.inventory.ItemStack;

import java.util.List;

// TODO: Add one time buy option
public class EItem extends DataModel {
    public String name;
    public List<String> description;
    public int slot;
    public ItemStack displayItem;
    public double buyPrice;
    public EReward buyReward;
    public EReward sellReward;
}
