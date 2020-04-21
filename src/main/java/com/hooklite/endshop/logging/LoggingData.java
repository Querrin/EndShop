package com.hooklite.endshop.logging;

import org.bukkit.ChatColor;

public class LoggingData {
    public static final String PREFIX;

    static {
        PREFIX = String.format("%s[%sEndShop%s]%s",
                ChatColor.DARK_GRAY,
                ChatColor.DARK_PURPLE,
                ChatColor.DARK_GRAY,
                ChatColor.RESET);
    }
}
