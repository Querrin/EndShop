package com.hooklite.endshop.data.models;

import com.hooklite.endshop.data.rewards.EReward;
import org.bukkit.Material;

import java.util.List;

public class EItem extends DataModel {
    public String name;
    public List<String> description;
    public int slot;
    public Material displayItem;
    public double buyPrice;
    public double sellPrice;
    public EReward reward;
}
