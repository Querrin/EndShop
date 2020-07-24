package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;

public class LocaleConfig {

    @YamlKey("language")
    public String language;

    @YamlKey("shops-menu-title")
    public String shopsMenuTitle;

    @YamlKey("next-page-item")
    public String nextPageItem;

    @YamlKey("next-page-material")
    public String nextPageMaterial;

    @YamlKey("previous-page-item")
    public String previousPageItem;

    @YamlKey("previous-page-material")
    public String previousPageMaterial;

    @YamlKey("display-page-item")
    public String displayPageItem;

    @YamlKey("display-page-material")
    public String displayPageMaterial;

    @YamlKey("back-item")
    public String backItem;

    @YamlKey("back-material")
    public String backMaterial;
}
