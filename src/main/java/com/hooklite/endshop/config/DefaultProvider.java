package com.hooklite.endshop.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class DefaultProvider {
    private static YamlConfiguration config;

    public static void loadConfig(Plugin plugin) {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = (YamlConfiguration) plugin.getConfig();
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
