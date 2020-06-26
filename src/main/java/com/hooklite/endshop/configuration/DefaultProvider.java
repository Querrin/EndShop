package com.hooklite.endshop.configuration;

import com.hooklite.endshop.EndShop;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class DefaultProvider {
    private static YamlConfiguration config;

    static {
        config = (YamlConfiguration) EndShop.getPlugin().getConfig();
        EndShop.getPlugin().saveDefaultConfig();
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static void reloadConfig() {
        EndShop.getPlugin().reloadConfig();
        config = (YamlConfiguration) EndShop.getPlugin().getConfig();
    }

    public static boolean debug() {
        return config.getBoolean("debug", false);
    }

    public static boolean logging() {
        return config.getBoolean("logging", true);
    }

    public static List<String> shops() {
        return config.getStringList("shops");
    }
}
