package com.hooklite.endshop.data.rewards.types;

import com.hooklite.endshop.data.rewards.EItemReward;
import com.hooklite.endshop.data.rewards.EReward;

public class EItemRewardType implements ERewardType {
    public String getType() {
        return "item";
    }

    public EReward getRewardObject() {
        return new EItemReward();
    }
}
