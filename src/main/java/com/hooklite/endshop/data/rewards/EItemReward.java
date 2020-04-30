package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.config.Transaction;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EItemReward implements EReward {
    private Material rewardMaterial;
    private RewardAction action;

    private EItem eItem;
    private ItemStack rewardItem;
    private int amount;

    @Override
    public void executeReward(EItem eItem, Player player, int amount) {
        double price = eItem.buyPrice * amount;
        Inventory playerInventory = player.getInventory();

        this.eItem = eItem;
        this.rewardItem = new ItemStack(rewardMaterial, 1);
        this.amount = amount;

        if(action == RewardAction.BUY) {

            if(rewardItem.getMaxStackSize() > 1) {
                boolean match = false;

                if(playerInventory.containsAtLeast(rewardItem, 1)) {
                    for(ItemStack buyItem : playerInventory) {
                        if(buyItem != null && buyItem.getAmount() < buyItem.getMaxStackSize() && rewardItem.isSimilar(buyItem)) {
                            if(buyItem.getAmount() < rewardItem.getMaxStackSize() - amount + 1) {
                                buyTransaction(player, price);
                                match = true;

                                break;
                            }
                        }
                    }
                }
                if(!match && getEmptySlots(player) >= Math.ceil((double) amount / rewardItem.getMaxStackSize())) {
                    buyTransaction(player, price);
                }
                else if(!match) {
                    MessageSender.toPlayer(player, "You do not have enough inventory space!");
                }
            }
            else {
                if(getEmptySlots(player) >= amount) {
                    buyTransaction(player, price);
                }
                else {
                    MessageSender.toPlayer(player, "You do not have enough inventory space!");
                }
            }

        }
        else {
            ItemStack itemToSell = new ItemStack(eItem.displayItem.getType(), amount);

            if(playerInventory.containsAtLeast(itemToSell, amount)) {
                if(rewardItem.getMaxStackSize() > 1) {
                    boolean match = false;
                    if(playerInventory.containsAtLeast(rewardItem, 1)) {
                        for(ItemStack item : playerInventory) {
                            if(item != null && item.getAmount() < item.getMaxStackSize() && rewardItem.isSimilar(item)) {
                                if(item.getAmount() < rewardItem.getMaxStackSize() - amount + 1) {
                                    sellTransaction(player, itemToSell);
                                    match = true;

                                    break;
                                }
                            }
                        }
                    }
                    if(!match && getEmptySlots(player) >= Math.ceil((double) amount / rewardItem.getMaxStackSize())) {
                        sellTransaction(player, itemToSell);
                    }
                    else if(!match) {
                        MessageSender.toPlayer(player, "You do not have enough inventory space!");
                    }
                }
                else {
                    if(getEmptySlots(player) >= amount) {
                        sellTransaction(player, itemToSell);
                    }
                    else {
                        MessageSender.toPlayer(player, "You do not have enough inventory space!");
                    }
                }
            }
            else {
                MessageSender.toPlayer(player, "You do not have enough items to sell!");
            }
        }
    }

    @Override
    public void setReward(String reward) {
        this.rewardMaterial = Material.matchMaterial(reward);
    }

    private void addItems(Player player, ItemStack rewardItem, int amount) {
        for(int i = 0; i < amount; i++) {
            player.getInventory().addItem(rewardItem);
        }
    }

    private void buyTransaction(Player player, double price) {
        if(Transaction.withdraw(player, price)) {
            addItems(player, rewardItem, amount);
            MessageSender.buyMessage(player, eItem.name, price, amount);
        }
        else {
            MessageSender.toPlayer(player, "You do not have enough balance!");
        }
    }

    private void sellTransaction(Player player, ItemStack sellItem) {
        player.getInventory().removeItem(sellItem);
        addItems(player, rewardItem, amount);

        MessageSender.sellMessage(player, eItem.name, rewardMaterial.name().replace("_", " ").toLowerCase(), amount);
    }

    @Override
    public String getReward() {
        return rewardMaterial.name();
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

    @Override
    public String getType() {
        return "item";
    }

    @Override
    public EReward getInstance() {
        return new EItemReward();
    }

    @Override
    public void setAction(RewardAction action) {
        this.action = action;
    }
}
