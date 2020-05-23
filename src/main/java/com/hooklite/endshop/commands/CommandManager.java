package com.hooklite.endshop.commands;

import com.hooklite.endshop.Information;
import com.hooklite.endshop.commands.subcommands.AddShopCommand;
import com.hooklite.endshop.commands.subcommands.ReloadCommand;
import com.hooklite.endshop.commands.subcommands.SubCommand;
import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.config.InventoryFactory;
import com.hooklite.endshop.logging.MessageSender;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    private final List<SubCommand> subCommands = new ArrayList<>();
    private final List<String> subCommandNames = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new ReloadCommand());
        subCommands.add(new AddShopCommand());

        for(SubCommand subcommand : subCommands) {
            subCommandNames.add(subcommand.getName());
        }
    }

    /**
     * Manages and routes the commands and subcommands that the plugins contains.
     * TODO: Split this big function into multiple parts.
     *
     * @param sender  The sender of the command.
     * @param command The name of the command.
     * @param label   The alias used.
     * @param args    Additional command arguments.
     * @return If the command was executed successfully.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Permission perms = Configuration.getPerms();
        Player player = sender instanceof Player ? (Player) sender : null;

        if(command.getName().equalsIgnoreCase("shop")) {
            if(sender instanceof Player) {
                if(CommandStatus.isShopCommandEnabled()) {
                    if(perms.has(player, "endshop.shop")) {
                        ((Player) sender).openInventory(InventoryFactory.getShopMenu(Configuration.getShops(), (Player) sender));
                    }
                    else {
                        MessageSender.noPermission(player, "endshop.shop");
                    }
                }
                else {
                    MessageSender.toPlayer(player, ChatColor.RED + "Shop is disabled! (improper config)");
                    MessageSender.toConsole(ChatColor.RED + "Shop is disabled! (improper config)");
                }
            }
            else {
                MessageSender.noConsoleExecution();
            }
        }
        else if(args.length > 0 && command.getName().equals("endshop")) {
            for(int i = 0; i < subCommands.size(); i++) {
                if(subCommands.get(i).getName().equalsIgnoreCase(args[0])) {
                    if(sender instanceof Player) {
                        if(perms.has(player, subCommands.get(i).getPermission())) {
                            subCommands.get(i).execute(sender, args);
                        }
                        else {
                            MessageSender.noPermission(player, subCommands.get(i).getPermission());
                        }
                    }
                    else {
                        subCommands.get(i).execute(sender, args);
                    }

                    break;
                }
                else if(i == subCommands.size() - 1) {
                    for(int j = 0; j < subCommands.size(); j++) {
                        if(subCommands.get(j).getName().startsWith(args[0].toLowerCase()) || subCommands.get(j).getName().equalsIgnoreCase(args[0])) {
                            String message = String.format("%s\n%sUsage: %s%s", subCommands.get(j).getDescription(), ChatColor.LIGHT_PURPLE, ChatColor.RESET, subCommands.get(j).getSyntax());

                            if(sender instanceof Player)
                                MessageSender.toPlayer((Player) sender, message);
                            else
                                MessageSender.toConsole(message);

                            break;
                        }
                        else if(j == subCommands.size() - 1) {
                            String message = "Unknown command.";

                            if(sender instanceof Player)
                                MessageSender.toPlayer((Player) sender, message);
                            else
                                MessageSender.toConsole(message);
                        }
                    }
                }
            }
        }
        else if(command.getName().equals("endshop")) {
            for(String str : Information.INFO_MESSAGES) {
                if(sender instanceof Player)
                    if(perms.has(player, "endshop")) {
                        MessageSender.toPlayer(player, str);
                    }
                    else {
                        MessageSender.noPermission(player, "endshop");
                        break;
                    }
                else
                    MessageSender.toConsole(str);
            }
        }

        return true;
    }

    /**
     * Provides tab completion for subcommands.
     *
     * @param sender  The sender of the command.
     * @param command The command sent.
     * @param alias   The alias used.
     * @param args    Additional command arguments to be autocompleted if they exist.
     * @return A list of possible arguments.
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equals("endshop")) {
            if(args.length == 1) {
                if(args[0].isEmpty()) {
                    return subCommandNames;
                }
                else {
                    ArrayList<String> typeCommands = new ArrayList<>();
                    for(String s : subCommandNames) {
                        if(s.startsWith(args[0])) {
                            typeCommands.add(s);
                        }
                    }
                    return typeCommands;
                }
            }
            else if(args.length > 1) {
                for(SubCommand subCommand : subCommands) {
                    if(subCommand.getName().equalsIgnoreCase(args[1])) {
                        return subCommand.getArguments((Player) sender, args);
                    }
                }
            }
        }

        return null;
    }

}
