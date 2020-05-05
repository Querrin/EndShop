package com.hooklite.endshop.data.models;

import com.hooklite.endshop.data.requirements.Requirement;
import com.hooklite.endshop.data.rewards.Reward;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.List;

// TODO: Add one time buy option
public class Item extends DataModel implements Serializable {
    public String name;
    public List<String> description;
    public int slot;
    public ItemStack displayItem;
    public Requirement buyReq;
    public Requirement sellReq;
    public Reward buyReward;
    public Reward sellReward;
    public boolean buyable;
    public boolean sellable;
}
