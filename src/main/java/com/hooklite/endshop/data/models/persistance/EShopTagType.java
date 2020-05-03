package com.hooklite.endshop.data.models.persistance;

import com.hooklite.endshop.data.models.EShop;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class EShopTagType implements PersistentDataType<EShop, EShop> {
    private static final EShopTagType TAG_TYPE;

    static {
        TAG_TYPE = new EShopTagType();
    }

    public static EShopTagType getInstance() {
        return TAG_TYPE;
    }

    @Override
    public Class<EShop> getPrimitiveType() {
        return EShop.class;
    }

    @Override
    public Class<EShop> getComplexType() {
        return EShop.class;
    }

    @Override
    public EShop toPrimitive(EShop complex, PersistentDataAdapterContext context) {
        return null;
    }

    @Override
    public EShop fromPrimitive(EShop primitive, PersistentDataAdapterContext context) {
        return null;
    }
}
