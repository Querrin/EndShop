package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EItemReward implements EReward {
    private Material rewardMaterial;

    @Override
    public boolean execute(Item eItem, Player player, int amount) {
        ItemStack reward = new ItemStack(rewardMaterial, 1);
        Inventory playerInventory = player.getInventory();

        if(reward.getMaxStackSize() > 1) {
            if(playerInventory.containsAtLeast(reward, 1)) {
                for(ItemStack item : playerInventory) {
                    if(item != null && item.getAmount() < item.getMaxStackSize() && reward.isSimilar(item)) {
                        if(item.getAmount() < reward.getMaxStackSize() - amount + 1) {
                            addItems(player, reward, amount);
                            return true;
                        }
                    }
                }
            }
            if(getEmptySlots(player) >= Math.ceil((double) amount / reward.getMaxStackSize())) {
                addItems(player, reward, amount);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(getEmptySlots(player) >= amount) {
                addItems(player, reward, amount);
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public String getReward(int ignore) {
        String materialName = rewardMaterial.name();
        materialName = materialName.replace("_", " ").toLowerCase();

        return materialName.substring(0, 1).toUpperCase() + materialName.substring(1);
    }

    @Override
    public String getFailedMessage() {
        return "You do not have enough inventory space!";
    }

    @Override
    public void setReward(String reward) {
        this.rewardMaterial = Material.matchMaterial(reward);
    }

    @Override
    public String getType() {
        return "item";
    }

    @Override
    public EReward getInstance() {
        return new EItemReward();
    }

    private void addItems(Player player, ItemStack reward, int amount) {
        for(int i = 0; i < amount; i++) {
            player.getInventory().addItem(reward);
        }
    }

    private int getEmptySlots(Player player) {
        int amount = 0;
        Inventory inventory = player.getInventory();

        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        for(int i = 0; i < inventory.getContents().length; i++) {
            ItemStack currentItem = inventory.getContents()[i];

            if(currentItem == null)
                amount++;
            else {
                if(currentItem == helmet || currentItem == chestplate || currentItem == leggings || currentItem == boots)
                    amount--;
            }
        }

        for(ItemStack ignored : player.getInventory().getExtraContents()) {
            amount--;
        }

        if(helmet == null)
            amount--;
        if(chestplate == null)
            amount--;
        if(leggings == null)
            amount--;
        if(boots == null)
            amount--;

        return amount;
    }
}
