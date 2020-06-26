package com.hooklite.endshop.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ShopsProvider {
    private static List<YamlConfiguration> configs;

    static {
        loadConfigs();
    }

    public static List<YamlConfiguration> getConfigs() {
        return configs;
    }

    public static void loadConfigs() {
        File file = new File(EndShop.getPlugin().getDataFolder().getPath().concat("shops/"));

        try {
            if(!file.exists()) {
                if(!file.mkdir())
                    throw new IOException("Unable to create shops directory.");
            }

            for(String shop : DefaultProvider.shops()) {
                File shopFile = new File(EndShop.getPlugin().getDataFolder().getPath().concat("shops/").concat(shop));

                if(!shopFile.exists()) {
                    YamlConfiguration internalConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(MessagesProvider.class.getResourceAsStream("/example.yml")));

                    internalConfig.save(shopFile);
                }
            }
        }
        catch(IOException e) {
            MessageSender.printStackTrace(e);
            MessageSender.logError("Unable to create shops directory or it's files.");

            Bukkit.getPluginManager().disablePlugin(EndShop.getPlugin());
        }
    }
}
