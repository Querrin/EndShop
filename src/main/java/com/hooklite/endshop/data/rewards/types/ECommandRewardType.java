package com.hooklite.endshop.data.rewards.types;

import com.hooklite.endshop.data.rewards.ECommandReward;
import com.hooklite.endshop.data.rewards.EReward;

public class ECommandRewardType implements ERewardType {
    public String getType() {
        return "command";
    }

    public EReward getRewardObject() {
        return new ECommandReward();
    }
}
