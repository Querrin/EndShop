package com.hooklite.endshop.holders;

import com.hooklite.endshop.data.models.Page;
import com.hooklite.endshop.data.models.Shop;

public interface PluginHolder {
    Shop getShop();
    Page getPage();
}
