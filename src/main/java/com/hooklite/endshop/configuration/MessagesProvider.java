package com.hooklite.endshop.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessagesProvider {
    private static YamlConfiguration config;

    static {
        loadConfig();
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    private static void loadConfig() {
        File file = new File(EndShop.getPlugin().getDataFolder().getPath() + "messages.yml");

        try {
            if(!file.exists()) {
                YamlConfiguration internalConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(MessagesProvider.class.getResourceAsStream("/messages.yml")));

                internalConfig.save(file);
            }

            config = new YamlConfiguration();
            config.load(file);
        }
        catch(InvalidConfigurationException e) {
            e.printStackTrace();
            MessageSender.logError("There is an error in your messages.yml file.");
        }
        catch(IOException e) {
            MessageSender.printStackTrace(e);
            MessageSender.logError("Failed creating messages.yml file.");

            Bukkit.getPluginManager().disablePlugin(EndShop.getPlugin());
        }
    }

}
