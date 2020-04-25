package com.hooklite.endshop.data.rewards;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EItemReward implements EReward {
    private ItemStack reward;
    private RewardAction action;

    @Override
    public void executeReward(Player player) {
        // TODO: Set the reward action
    }

    @Override
    public RewardAction getRewardAction() {
        return action;
    }

    @Override
    public void setRewardAction(RewardAction action) {
        this.action = action;
    }

    @Override
    public void setReward(String reward) {
        this.reward = new ItemStack(Material.matchMaterial(reward), 1);
    }
}
