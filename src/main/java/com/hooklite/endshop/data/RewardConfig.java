package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;

public class RewardConfig {

    @YamlKey("type")
    public String type;

    @YamlKey("amount")
    public int amount;

    @YamlKey("reward")
    public String reward;
}
