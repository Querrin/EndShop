package com.hooklite.endshop;

import com.hooklite.endshop.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndShop extends JavaPlugin {

    @Override
    public void onEnable() {
        Configuration.initDefaultConfigs();
        Configuration.loadShopConfigs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
