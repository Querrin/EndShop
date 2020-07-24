package com.hooklite.endshop.data.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.data.DefaultConfig;
import com.hooklite.endshop.data.YamlResolver;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class DefaultProvider {
    private static DefaultConfig config;

    public static DefaultConfig getConfig() {
        return config;
    }

    public static void loadConfig() throws IllegalAccessException {
        EndShop.getPlugin().reloadConfig();

        config = YamlResolver.resolveValues((YamlConfiguration) EndShop.getPlugin().getConfig(), new DefaultConfig());

        EndShop.getPlugin().saveDefaultConfig();
    }

    public static boolean debug() {
        return config.debug;
    }

    public static boolean logging() {
        return config.logging;
    }

    public static List<String> shops() {
        return config.shops;
    }
}
