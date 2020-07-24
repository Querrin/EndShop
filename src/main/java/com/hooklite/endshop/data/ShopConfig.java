package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;

import java.util.List;

public class ShopConfig {

    @YamlKey("title")
    public String title;

    @YamlKey("description")
    public List<String> description;

    @YamlKey("display-item")
    public String displayItem;

    @YamlKey("show-conditions")
    public boolean showConditions;

    @YamlKey("show-rewards")
    public boolean showRewards;

    @YamlKey("slot")
    public int slot;

    public List<ItemConfig> items;
}
