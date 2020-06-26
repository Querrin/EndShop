package com.hooklite.endshop.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

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
                if(!file.createNewFile())
                    throw new IOException("Unable to create messages.yml file.");
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
            MessageSender.logError(e.getMessage());

            Bukkit.getPluginManager().disablePlugin(EndShop.getPlugin());
        }
    }

}
