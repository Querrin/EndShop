package com.hooklite.endshop;

import com.hooklite.endshop.config.DefaultProvider;
import com.hooklite.endshop.config.LanguageProvider;
import com.hooklite.endshop.config.ShopsProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EndShop extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            DefaultProvider.loadConfig(this);
            LanguageProvider.loadConfig(this);
            ShopsProvider.loadConfigs(this);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

    }
}
