package com.hooklite.endshop.listeners.action;

import com.hooklite.endshop.data.conditions.ERequirement;
import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.config.ItemLoader;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.events.BuyEvent;
import com.hooklite.endshop.events.SellEvent;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class TransactionListener implements Listener {
    @EventHandler
    public void onBuy(BuyEvent event) {
        EItem item = getItem(event.getItem());
        Player player = event.getWhoClicked();
        int amount = event.getClickedItem().getItemMeta().getPersistentDataContainer().get(ItemLoader.AMOUNT_KEY, PersistentDataType.INTEGER);
        EReward reward = Objects.requireNonNull(item).buyReward;
        ERequirement req = item.buyReq;

        if(req.check(player, amount) && req.doTransaction(player, amount)) {
            if(reward.execute(item, player, amount)) {
                MessageSender.buyMessage(player, req.getName(amount), reward.getReward(amount), amount);
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

    @EventHandler
    public void onSell(SellEvent event) {
        EItem item = getItem(event.getItem());
        Player player = event.getWhoClicked();
        int amount = event.getClickedItem().getItemMeta().getPersistentDataContainer().get(ItemLoader.AMOUNT_KEY, PersistentDataType.INTEGER);
        EReward reward = Objects.requireNonNull(item).sellReward;
        ERequirement req = item.sellReq;

        if(req.check(player, amount) && req.doTransaction(player, amount)) {
            if(reward.execute(item, player, amount)) {
                MessageSender.sellMessage(player, req.getName(amount), reward.getReward(amount), amount);
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

    private EItem getItem(ItemStack item) {
        for(EShop shop : Configuration.getShops()) {
            for(EPage page : shop.pages) {
                for(EItem eItem : page.getItems()) {
                    if(item.equals(eItem.displayItem)) {
                        return eItem;
                    }
                }
            }
        }

        return null;
    }
}
