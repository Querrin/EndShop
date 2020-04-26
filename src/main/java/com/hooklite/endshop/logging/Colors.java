package com.hooklite.endshop.logging;

import org.bukkit.ChatColor;

public class Colors {

    /**
     * Replaces the minecraft color codes with compatible types.
     *
     * @param string A string that contains minecraft color codes.
     * @return A colored string.
     */
    public static String loadColors(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
