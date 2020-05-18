package com.hooklite.endshop.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {

    /**
     * Returns the name of the subcommmand.
     *
     * @return Subcommand name.
     */
    String getName();

    /**
     * Returns the description of the subcommmand.
     *
     * @return Subcommand description.
     */
    String getDescription();

    /**
     * Returns the permission that is needed for executing the subcommmand.
     *
     * @return Subcommand permission.
     */
    String getPermission();

    /**
     * Returns the syntax of the subcommmand, representing the uses.
     *
     * @return Subcommand syntax.
     */
    String getSyntax();

    /**
     * Executes the subcommand.
     *
     * @param sender The sender of the command.
     * @param args   Additional command arguments.
     */
    void execute(CommandSender sender, String[] args);

    List<String> getArguments(Player player, String[] args);
}
