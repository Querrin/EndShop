package com.hooklite.endshop.shop.schedules;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class OpenInventorySchedule extends BukkitRunnable {
    private final Player player;
    private final Inventory inventory;

    public OpenInventorySchedule(Player player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        player.openInventory(inventory);
    }
}
