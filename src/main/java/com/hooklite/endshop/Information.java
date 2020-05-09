package com.hooklite.endshop;

import com.hooklite.endshop.config.Configuration;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Information {
    public static final String AUTHOR = "Querrin";
    public static final String GITHUB = "https://github.com/Querrin";
    public static final String REPOSITORY = "https://github.com/Querrin/EndShop";
    public static final String DISCORD_SERVER = "http://discord.hooklite.com";
    public static final List<String> INFO_MESSAGES = new ArrayList<>();

    static {
        INFO_MESSAGES.add(ChatColor.BLUE + "Running EndShop " + Configuration.getPlugin().getDescription().getVersion());
        INFO_MESSAGES.add(ChatColor.LIGHT_PURPLE + "Made by " + ChatColor.RESET + AUTHOR);
        INFO_MESSAGES.add(ChatColor.LIGHT_PURPLE + "GitHub: " + ChatColor.RESET + REPOSITORY);
        INFO_MESSAGES.add(ChatColor.LIGHT_PURPLE + "Discord: " + ChatColor.RESET + DISCORD_SERVER);
    }
}
