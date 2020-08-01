package com.hooklite.endshop;

import com.hooklite.endshop.config.DefaultProvider;
import com.hooklite.endshop.config.LanguageProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EndShop extends JavaPlugin {

    @Override
    public void onEnable() {
        DefaultProvider.loadConfig(this);
        LanguageProvider.loadConfig(this);
    }

    @Override
    public void onDisable() {

    }
}
