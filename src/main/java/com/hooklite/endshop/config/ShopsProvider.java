package com.hooklite.endshop.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopsProvider {
    private static Map<String, YamlConfiguration> configs;

    public static void loadConfigs(Plugin plugin) throws IOException {
        configs = new HashMap<>();

        List<String> fileNames = DefaultProvider.getConfig().getStringList("shops");

        File shopsFolder = new File(plugin.getDataFolder() + "/shops");

        if(!shopsFolder.mkdir()) throw new IOException();

        for(String fileName : fileNames) {
            File configFile = new File(plugin.getDataFolder() + "/shops/" + fileName);

            if(!configFile.exists()) {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(ShopsProvider.class.getResourceAsStream("/example.yml")));
                config.save(configFile);
            }

            // Possibly not working if it does not exist?
            configs.put(fileName, YamlConfiguration.loadConfiguration(configFile));
        }
    }

    public static Map<String, YamlConfiguration> getConfigs() {
        return configs;
    }
}
