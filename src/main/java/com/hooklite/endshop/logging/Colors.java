package com.hooklite.endshop.logging;

import org.bukkit.ChatColor;

public class Colors {
    public static String loadColors(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
