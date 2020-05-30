package com.hooklite.endshop.listeners;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.data.menus.BuyInventory;
import com.hooklite.endshop.data.menus.BuySellInventory;
import com.hooklite.endshop.data.menus.SellInventory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.requirements.Requirement;
import com.hooklite.endshop.data.rewards.Action;
import com.hooklite.endshop.data.rewards.Reward;
import com.hooklite.endshop.events.TransactionEvent;
import com.hooklite.endshop.holders.PluginHolder;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class TransactionListener implements Listener {
    @EventHandler
    public void onTransaction(TransactionEvent event) {
        Item item = getItem(event.getItem());
        Player player = event.getWhoClicked();
        int amount = event.getClickedItem().getItemMeta().getPersistentDataContainer().get(Configuration.AMOUNT_KEY, PersistentDataType.INTEGER);
        Reward reward = event.getAction() == Action.BUY ? item.buyReward : item.sellReward;
        Requirement req = event.getAction() == Action.BUY ? item.buyReq : item.sellReq;

        if(req.check(player, amount) && req.doTransaction(player, amount)) {
            if(reward.execute(item, player, amount)) {
                if(event.getAction() == Action.BUY)
                    MessageSender.buyMessage(player, req.getName(amount), reward.getReward(amount));
                else
                    MessageSender.sellMessage(player, req.getName(amount), reward.getReward(amount));
            }
            else {
                req.undoTransaction(player, amount);
                MessageSender.toPlayer(player, reward.getFailedMessage());
            }
        }
        else {
            MessageSender.toPlayer(player, req.getFailedMessage());
        }
    }

    private Item getItem(ItemStack item) {
        for(Shop shop : Configuration.getShops()) {
            for(Page page : shop.pages) {
                for(Item eItem : page.getItems()) {
                    if(item.equals(eItem.displayItem)) {
                        return eItem;
                    }
                }
            }
        }

        return null;
    }
}
