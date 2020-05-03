package com.hooklite.endshop.data.models.persistance;

import com.hooklite.endshop.data.models.EItem;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class EItemTagType implements PersistentDataType<EItem, EItem> {
    private static final EItemTagType TAG_TYPE;

    static {
        TAG_TYPE = new EItemTagType();
    }

    public static EItemTagType getInstance() {
        return TAG_TYPE;
    }

    @Override
    public Class<EItem> getPrimitiveType() {
        return EItem.class;
    }

    @Override
    public Class<EItem> getComplexType() {
        return EItem.class;
    }

    @Override
    public EItem toPrimitive(EItem complex, PersistentDataAdapterContext context) {
        return null;
    }

    @Override
    public EItem fromPrimitive(EItem primitive, PersistentDataAdapterContext context) {
        return null;
    }
}
