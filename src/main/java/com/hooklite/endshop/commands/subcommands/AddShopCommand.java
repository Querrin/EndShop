package com.hooklite.endshop.commands.subcommands;

import com.hooklite.endshop.data.config.Configuration;
import com.hooklite.endshop.logging.MessageSender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddShopCommand implements SubCommand {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Registers a new shop.";
    }

    @Override
    public String getPermission() {
        return "endshop.add";
    }

    @Override
    public String getSyntax() {
        return "/endshop add <name>";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // TODO: Check filename validity
        StringBuilder shopName = new StringBuilder();

        for(int i = 1; i < args.length; i++) {
            shopName.append(args[i]).append(" ");
        }

        if(shopName.length() > 0) {
            YamlConfiguration config = Configuration.getDefaultConfig();

            List<String> shops = config.getStringList("shops");
            if(!shops.contains(shopName.toString().trim())) {
                shops.add(shopName.toString().trim());

                try {
                    config.set("shops", shops);
                    config.save(new File(Configuration.getPlugin().getDataFolder(), "config.yml"));

                    String message = String.format("Registered new shop \"%s\"", shopName.toString().trim());

                    if(sender instanceof Player)
                        MessageSender.toPlayer((Player) sender, message);
                    else
                        MessageSender.toConsole(message);

                    Configuration.reloadPlugin();

                }
                catch(IOException e) {
                    String message = ChatColor.RED + "Failed to add shop!";
                    e.printStackTrace();
                    if(sender instanceof Player)
                        MessageSender.toPlayer((Player) sender, message);
                    else
                        MessageSender.toConsole(message);
                }
            }
            else {
                String message = ChatColor.RED + "Invalid shop name!";

                if(sender instanceof Player)
                    MessageSender.toPlayer((Player) sender, message);
                else
                    MessageSender.toConsole(message);
            }
        }
    }


    @Override
    public List<String> getArguments(Player player, String[] args) {
        return null;
    }
}
