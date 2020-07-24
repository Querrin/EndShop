package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;

public class ConditionConfig {

    @YamlKey("type")
    public String type;

    @YamlKey("amount")
    public int amount;

    @YamlKey("condition")
    public String condition;
}
