package com.hooklite.endshop.data.models.persistance;

import com.hooklite.endshop.data.models.Page;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class PageTagType implements PersistentDataType<byte[], Page> {
    private static final PageTagType TAG_TYPE;

    static {
        TAG_TYPE = new PageTagType();
    }

    public static PageTagType getInstance() {
        return TAG_TYPE;
    }


    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<Page> getComplexType() {
        return Page.class;
    }

    @Override
    public byte[] toPrimitive(Page complex, PersistentDataAdapterContext context) {
        return Serializer.serialize(complex);
    }

    @Override
    public Page fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        return (Page) Serializer.deserialize(primitive);
    }
}
