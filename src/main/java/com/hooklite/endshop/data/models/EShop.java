package com.hooklite.endshop.data.models;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EShop extends EDataModel {
    public String title;
    public List<String> description;
    public ItemStack displayItem;
    public int slot;
    public List<EPage> pages = new ArrayList<>();
    public YamlConfiguration config;
}