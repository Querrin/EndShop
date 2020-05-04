package com.hooklite.endshop.data.rewards;

import com.hooklite.endshop.data.models.Item;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ECommandReward implements EReward {
    private String reward;

    @Override
    public boolean execute(Item item, Player player, int amount) {
        String command = reward;
        command = command.replace("%player%", player.getName());

        if(command.contains("(CONSOLE)")) {
            command = command.replace("(CONSOLE)", "").trim();
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);

            return true;
        }
        else if(command.contains("(PLAYER)")) {
            command = command.replace("(PLAYER)", "").trim();
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);

            return true;
        }
        else {
            // TODO: Better improperly configured command system (Possibly logging)
            MessageSender.toConsole(String.format("%sCommand executor of \"%s\" is improperly configured!", ChatColor.RED, command));
            MessageSender.toPlayer(player, String.format("%sCommand executor of \"%s\" is improperly configured!", ChatColor.RED, command));
            return false;
        }
    }

    @Override
    public String getReward(int ignore) {
        return reward;
    }

    @Override
    public String getFailedMessage() {
        return String.format("%sMessage the admins to fix the error and get your reward!", ChatColor.RED);
    }

    @Override
    public void setReward(String reward) {
        this.reward = reward;
    }

    @Override
    public String getType() {
        return "command";
    }

    @Override
    public EReward getInstance() {
        return new ECommandReward();
    }
}
