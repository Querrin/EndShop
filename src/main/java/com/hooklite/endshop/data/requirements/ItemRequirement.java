package com.hooklite.endshop.data.requirements;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemRequirement implements Requirement {
    private Material requirement;
    private int configAmount;

    @Override
    public String getType() {
        return "item";
    }

    @Override
    public boolean check(Player player, int amount) {
        ItemStack requiredItem = new ItemStack(requirement, 1);

        return player.getInventory().containsAtLeast(requiredItem, amount * configAmount);
    }

    @Override
    public boolean doTransaction(Player player, int amount) {
        ItemStack requiredItem = new ItemStack(requirement);
        Inventory playerInventory = player.getInventory();

        for(int i = 0; i < amount * configAmount; i++) {
            playerInventory.removeItem(requiredItem);
        }

        return true;
    }

    @Override
    public boolean undoTransaction(Player player, int amount) {
        ItemStack requiredItem = new ItemStack(requirement, 1);
        Inventory playerInventory = player.getInventory();

        for(int i = 0; i < amount * configAmount; i++) {
            playerInventory.addItem(requiredItem);
        }

        return true;
    }

    @Override
    public String getFailedMessage() {
        return "You do not have enough items!";
    }

    @Override
    public void setRequirement(String requirement) throws InvalidConfigurationException {
        Material material = Material.matchMaterial(requirement);

        if(material != null)
            this.requirement = material;
        else
            throw new InvalidConfigurationException("The requirement is improperly configured!");
    }

    @Override
    public void setAmount(int amount) {
        this.configAmount = amount;
    }

    @Override
    public String getName(int amount) {
        String materialName = requirement.name();
        materialName = materialName.replace("_", " ").toLowerCase();

        return String.format("%sx %s", amount * configAmount, materialName.substring(0, 1).toUpperCase() + materialName.substring(1));
    }

    @Override
    public Requirement getInstance() {
        return new ItemRequirement();
    }

    @Override
    public int getMaxAmount(Player player) {
        int amount = 0;
        ItemStack[] items = player.getInventory().getContents();
        ItemStack req = new ItemStack(requirement, 1);

        for(ItemStack itemStack : items) {
            if(itemStack != null && itemStack.isSimilar(req))
                amount += itemStack.getAmount();
        }

        return amount;
    }

}
