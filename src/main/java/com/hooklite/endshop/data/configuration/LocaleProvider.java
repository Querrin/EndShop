package com.hooklite.endshop.data.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocaleProvider {
    private static YamlConfiguration config;

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static void loadConfig() {
        File file = new File(EndShop.getPlugin().getDataFolder().getPath() + "languages/locale_EN.yml");

        try {
            MessageSender.logDebug("Attempting to load locales file...");

            if(!file.exists()) {
                YamlConfiguration internalConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(LocaleProvider.class.getResourceAsStream("/languages/locale_EN.yml")));

                internalConfig.save(file);
            }

            config = new YamlConfiguration();
            config.load(file);
        }
        catch(InvalidConfigurationException e) {
            e.printStackTrace();
            MessageSender.logError("There is an error in your locale_EN.yml file.");
        }
        catch(IOException e) {
            MessageSender.printStackTrace(e);
            MessageSender.logError("Failed creating locale_EN.yml file.");

            Bukkit.getPluginManager().disablePlugin(EndShop.getPlugin());
        }
    }

}
