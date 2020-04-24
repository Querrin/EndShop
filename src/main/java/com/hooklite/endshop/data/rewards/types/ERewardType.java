package com.hooklite.endshop.data.rewards.types;

import com.hooklite.endshop.data.rewards.EReward;

public interface ERewardType {
    String getType();

    EReward getRewardObject();
}
