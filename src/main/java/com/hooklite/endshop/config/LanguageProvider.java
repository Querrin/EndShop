package com.hooklite.endshop.config;

import com.hooklite.endshop.logging.MessageManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LanguageProvider {
    private static final List<String> defaultLanguages = new ArrayList<>();
    private static YamlConfiguration config;

    static {
        defaultLanguages.add("lang_en.yml");
    }

    public static void loadConfig(Plugin plugin) throws IOException {
        loadDefaults(plugin);
        String language = DefaultProvider.getConfig().getString("language");

        File langFile = new File(plugin.getDataFolder().toString() + "/languages/" + language);

        if(!langFile.exists()) {
            MessageManager.logError("Language file does not exist, using defaults...");
            config = YamlConfiguration.loadConfiguration(new InputStreamReader(LanguageProvider.class.getResourceAsStream("/languages/lang_en.yml")));
            return;
        }

        config = YamlConfiguration.loadConfiguration(langFile);
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    private static void loadDefaults(Plugin plugin) throws IOException {
        File langFolder = new File(plugin.getDataFolder().toString() + "/languages/");

        if(!langFolder.mkdir())
            throw new IOException();

        for(String language : defaultLanguages) {
            File file = new File(plugin.getDataFolder().toString() + "/languages/" + language);

            if(!file.exists()) {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(LanguageProvider.class.getResourceAsStream("/languages/" + language)));
                config.save(file);
            }
        }
    }
}
