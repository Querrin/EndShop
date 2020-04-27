package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.logging.MessageLogger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EItemReward implements EReward {
    private Material reward;

    @Override
    public void executeReward(Player player, RewardAction action, int amount) {
        Inventory playerInventory = player.getInventory();
        ItemStack item = new ItemStack(reward, amount);

        if (action == RewardAction.BUY) {

            if (item.getMaxStackSize() > 1) {
                if (!playerInventory.containsAtLeast(item, 65 - amount))
                    playerInventory.addItem(item);
                else if (hasEmptySlot(playerInventory))
                    playerInventory.addItem(item);
                else
                    MessageLogger.toPlayer(player, "You do not have enough inventory space!");

            } else {
                if (getEmptySlots(playerInventory) >= amount)
                    playerInventory.addItem(item);
                else
                    MessageLogger.toPlayer(player, "You do not have enough inventory space!");
            }

        } else {
            // TODO: Possibly allows illegal item stacking ??
            if (playerInventory.containsAtLeast(item, amount)) {
                playerInventory.removeItem(item);
                MessageLogger.toPlayer(player, String.format("Successfully sold x%s %s.", amount, item.getType().toString().replace("_", " ").toLowerCase()));
            } else {
                MessageLogger.toPlayer(player, "You do not have enough items to sell!");
            }
        }
    }

    @Override
    public void setReward(String reward) {
        this.reward = Material.matchMaterial(reward);
    }

    private boolean hasEmptySlot(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item == null)
                return true;
        }

        return false;
    }

    private int getEmptySlots(Inventory inventory) {
        int amount = 0;

        for (ItemStack item : inventory.getContents()) {
            if (item == null)
                amount++;
        }

        return amount;
    }
}
