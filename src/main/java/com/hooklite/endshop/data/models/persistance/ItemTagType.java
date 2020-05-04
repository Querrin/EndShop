package com.hooklite.endshop.data.models.persistance;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class ItemTagType implements PersistentDataType<byte[], Item> {
    private static final ItemTagType TAG_TYPE;

    static {
        TAG_TYPE = new ItemTagType();
    }

    public static ItemTagType getInstance() {
        return TAG_TYPE;
    }

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<Item> getComplexType() {
        return Item.class;
    }

    @Override
    public byte[] toPrimitive(Item complex, PersistentDataAdapterContext context) {
        return Serializer.serialize(complex);
    }

    @Override
    public Item fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        return (Item) Serializer.deserialize(primitive);
    }
}
