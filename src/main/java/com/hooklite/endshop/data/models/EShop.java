package com.hooklite.endshop.data.models;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class EShop extends DataModel {
    public String title;
    public List<String> description;
    public Material displayItem;
    public int slot;
    public List<EPage> pages = new ArrayList<>();
    public ECurrency currency;
    public YamlConfiguration config;
}
