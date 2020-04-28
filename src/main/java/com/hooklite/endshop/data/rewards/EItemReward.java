package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.logging.MessageLogger;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EItemReward implements EReward {
    private Material reward;

    @Override
    public void executeReward(EItem eItem, Player player, RewardAction action, int amount) {
        double price = eItem.buyPrice;
        Inventory playerInventory = player.getInventory();
        ItemStack item = new ItemStack(reward, amount);
        Economy econ = Configuration.getEcon();

        if (action == RewardAction.BUY) {
            if (item.getMaxStackSize() > 1) {
                if (!playerInventory.containsAtLeast(item, 65 - amount)) {
                    playerInventory.addItem(item);
                    econ.withdrawPlayer(player, price * amount);
                    MessageLogger.sendBuyMessage(player, eItem.name, price * amount, amount);
                } else if (hasEmptySlot(playerInventory)) {
                    playerInventory.addItem(item);
                    econ.withdrawPlayer(player, price * amount);
                    MessageLogger.sendBuyMessage(player, eItem.name, price * amount, amount);
                } else
                    MessageLogger.toPlayer(player, "You do not have enough inventory space!");

            } else {
                if (getEmptySlots(playerInventory) >= amount) {
                    playerInventory.addItem(item);
                    econ.withdrawPlayer(player, price * amount);
                    MessageLogger.sendBuyMessage(player, eItem.name, price * amount, amount);
                } else
                    MessageLogger.toPlayer(player, "You do not have enough inventory space!");
            }

        } else {
            // FIXME: Possibly allows illegal item stacking ??
            if (playerInventory.containsAtLeast(item, amount)) {
                for (int i = 0; i < amount; i++) {
                    player.getInventory().removeItem(item);
                    playerInventory.addItem(item);
                }
                MessageLogger.sendSellMessage(player, eItem.name, reward.name().replace("_", " ").toLowerCase(), amount);
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

    @Override
    public String getReward() {
        return reward.name();
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
