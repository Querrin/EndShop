package com.hooklite.endshop.data.models.persistance;

import com.hooklite.endshop.data.models.Shop;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class ShopTagType implements PersistentDataType<Shop, Shop> {
    private static final ShopTagType TAG_TYPE;

    static {
        TAG_TYPE = new ShopTagType();
    }

    public static ShopTagType getInstance() {
        return TAG_TYPE;
    }

    @Override
    public Class<Shop> getPrimitiveType() {
        return Shop.class;
    }

    @Override
    public Class<Shop> getComplexType() {
        return Shop.class;
    }

    @Override
    public Shop toPrimitive(Shop complex, PersistentDataAdapterContext context) {
        return null;
    }

    @Override
    public Shop fromPrimitive(Shop primitive, PersistentDataAdapterContext context) {
        return null;
    }
}
