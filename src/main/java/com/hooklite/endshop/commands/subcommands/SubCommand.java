package com.hooklite.endshop.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {

    /**
     * Returns the name of a subcommand.
     * @return name of the subcommand
     */
    String getName();

    /**
     * Returns the description of a subcommand.
     * @return name of the subcommand
     */
    String getDescription();

    /**
     * Returns the syntax of a subcommand.
     * @return syntax of the subcommand
     */
    String getSyntax();

    /**
     * Executes the subcommand with the given parameters.
     * @param sender Sender of the command
     * @param args Arguments of the command
     */
    void execute(CommandSender sender, String[] args);

    /**
     * Automatically completes arguments of a subcommand
     * @param player Sender of the command
     * @param args Arguments of the command
     * @return A list of subcommands
     */
    List<String> getArguments(Player player, String[] args);
}
