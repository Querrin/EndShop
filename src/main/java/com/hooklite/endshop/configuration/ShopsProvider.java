package com.hooklite.endshop.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShopsProvider {
    private static List<YamlConfiguration> configs;

    public static List<YamlConfiguration> getConfigs() {
        return configs;
    }

    public static void loadConfigs() {
        MessageSender.logDebug("Attempting to load shop files...");

        File file = new File(EndShop.getPlugin().getDataFolder().getPath().concat("shops/"));
        configs = new ArrayList<>();

        try {
            if(!file.exists()) {
                if(!file.mkdir())
                    throw new IOException("Unable to create shops directory.");
            }

            for(String shop : DefaultProvider.shops()) {
                File shopFile = new File(EndShop.getPlugin().getDataFolder().getPath().concat("shops/").concat(shop));

                MessageSender.logDebug(String.format("Found file %s in config.yml", shop));

                if(!shopFile.exists()) {
                    MessageSender.logDebug(String.format("%s doesn't exist, loading example...", shop));

                    YamlConfiguration internalConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(LocaleProvider.class.getResourceAsStream("/example.yml")));

                    internalConfig.save(shopFile);
                }

                configs.add(YamlConfiguration.loadConfiguration(shopFile));
            }
        }
        catch(IOException e) {
            MessageSender.printStackTrace(e);
            MessageSender.logError("Unable to create shops directory or it's files.");

            Bukkit.getPluginManager().disablePlugin(EndShop.getPlugin());
        }
    }
}
