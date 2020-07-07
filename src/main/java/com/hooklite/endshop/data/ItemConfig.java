package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;

public class ItemConfig {
    private final String KEY;
    @YamlKey()
    public String title;

    public ItemConfig(String key) {
        KEY = key;
    }
}
