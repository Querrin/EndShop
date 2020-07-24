package com.hooklite.endshop.data.configuration;

import com.hooklite.endshop.EndShop;
import com.hooklite.endshop.MessageSender;
import com.hooklite.endshop.data.LocaleConfig;
import com.hooklite.endshop.data.YamlResolver;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocaleProvider {
    private static LocaleConfig config;

    public static LocaleConfig getConfig() {
        return config;
    }

    public static void loadConfig(String language) {
        File languageFolder = new File(EndShop.getPlugin().getDataFolder().getPath() + "languages");
        File defaultLocale = new File(EndShop.getPlugin().getDataFolder().getPath() + "languages/locale_EN.yml");

        try {
            MessageSender.logDebug("Attempting to create languages folder...");

            if(!languageFolder.mkdir())
                throw new IOException();

            MessageSender.logDebug("Attempting to load locales file...");

            if(!defaultLocale.exists()) {
                YamlConfiguration internalConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(LocaleProvider.class.getResourceAsStream("/languages/locale_EN.yml")));

                internalConfig.save(defaultLocale);
            }

            String[] localeFiles = new File(EndShop.getPlugin().getDataFolder().getPath() + "languages").list();

            for(String file : localeFiles) {
                MessageSender.logDebug(String.format("Found file %s in languages directory", file));

                if(file.contains(".") && file.substring(file.lastIndexOf(".")).equals(".yml")) {
                    File locale = new File(EndShop.getPlugin().getDataFolder().getPath() + "languages/" + file);

                    LocaleConfig localeConfig = new LocaleConfig();
                    YamlResolver.resolveValues(YamlConfiguration.loadConfiguration(locale), localeConfig);

                    if(localeConfig.language.equals(language)) {
                        config = localeConfig;
                        return;
                    }
                }
            }

            MessageSender.logError("Unable to find the selected language file, switching to EN...");

            config = new LocaleConfig();
        }
        catch(IOException e) {
            MessageSender.printStackTrace(e);
            MessageSender.logError("Failed creating locale_EN.yml file.");

            Bukkit.getPluginManager().disablePlugin(EndShop.getPlugin());
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
