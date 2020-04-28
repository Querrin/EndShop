package com.hooklite.endshop.listeners.action;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.data.models.EItem;
import com.hooklite.endshop.data.models.EPage;
import com.hooklite.endshop.data.models.EShop;
import com.hooklite.endshop.data.rewards.EReward;
import com.hooklite.endshop.data.rewards.RewardAction;
import com.hooklite.endshop.events.BuyEvent;
import com.hooklite.endshop.events.SellEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class TransactionListener implements Listener {
    @EventHandler
    public void onBuy(BuyEvent event) {
        EItem item = getItem(event.getItem());
        EReward reward = item.buyReward;

        reward.executeReward(item, event.getWhoClicked(), RewardAction.BUY, event.getAmount());
    }

    @EventHandler
    public void onSell(SellEvent event) {
        EItem item = getItem(event.getItem());
        EReward reward = item.sellReward;

        // TODO: Remove sell price, the reward for selling is already set wtf was I thinking

        reward.executeReward(item, event.getWhoClicked(), RewardAction.SELL, event.getAmount());
    }

    private EItem getItem(ItemStack item) {
        for (EShop shop : Configuration.getShops()) {
            for (EPage page : shop.pages) {
                for (EItem eItem : page.getItems()) {
                    if (item.equals(eItem.displayItem)) {
                        return eItem;
                    }
                }
            }
        }

        return null;
    }
}
