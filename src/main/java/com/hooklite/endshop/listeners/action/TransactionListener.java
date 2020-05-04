package com.hooklite.endshop.listeners.action;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.config.MenuItemFactory;
import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;
import com.hooklite.endshop.data.requirements.ERequirement;
import com.hooklite.endshop.data.rewards.EAction;
import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.events.TransactionEvent;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class TransactionListener implements Listener {
    @EventHandler
    public void onBuy(TransactionEvent event) {
        Item item = getItem(event.getItem());
        Player player = event.getWhoClicked();
        int amount = event.getClickedItem().getItemMeta().getPersistentDataContainer().get(MenuItemFactory.AMOUNT_KEY, PersistentDataType.INTEGER);
        EReward reward = Objects.requireNonNull(item).buyReward;
        ERequirement req = item.buyReq;

        if(event.getAction() == EAction.BUY) {
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
        else {
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
