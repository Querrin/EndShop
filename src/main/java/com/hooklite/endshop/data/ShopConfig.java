package com.hooklite.endshop.data;

import com.hooklite.endshop.KeyType;
import com.hooklite.endshop.annotations.YamlKey;
import com.hooklite.endshop.annotations.YamlKeyType;

import java.util.List;
import java.util.Set;

public class ShopConfig {

    @YamlKey("title")
    @YamlKeyType
    public String title;

    @YamlKey("description")
    @YamlKeyType
    public List<String> description;

    @YamlKey("display-item")
    @YamlKeyType
    public String displayItem;

    @YamlKey("show-conditions")
    @YamlKeyType
    public boolean showConditions;

    @YamlKey("show-rewards")
    @YamlKeyType
    public boolean showRewards;

    @YamlKey("slot")
    @YamlKeyType
    public int slot;

    @YamlKey("items")
    @YamlKeyType(KeyType.KEY_LIST)
    public Set<String> itemKeys;

    public List<ItemConfig> items;
}
