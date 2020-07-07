package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;

import java.util.List;

public class DefaultConfig {

    @YamlKey("language")
    public String language;

    @YamlKey("debug")
    public boolean debug;

    @YamlKey("logging")
    public boolean logging;

    @YamlKey("prefix")
    public String prefix;

    @YamlKey("shops")
    public List<String> shops;
}
