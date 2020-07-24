package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;

import java.util.List;

public class ItemConfig {

    @YamlKey("title")
    public String title;

    @YamlKey("description")
    public List<String> description;

    @YamlKey("display-item")
    public String displayItem;

    @YamlKey("buy")
    public boolean buy;

    @YamlKey("sell")
    public boolean sell;

    public List<ConditionConfig> conditions;

    public List<RewardConfig> rewards;
}
