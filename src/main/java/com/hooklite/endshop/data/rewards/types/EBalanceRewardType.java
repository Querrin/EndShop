package com.hooklite.endshop.data.rewards.types;

import com.hooklite.endshop.data.rewards.EBalanceReward;
import com.hooklite.endshop.data.rewards.EReward;

public class EBalanceRewardType implements ERewardType {
    public String getType() {
        return "balance";
    }

    public EReward getRewardObject() {
        return new EBalanceReward();
    }
}
