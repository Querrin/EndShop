package com.hooklite.endshop.data.models.persistance;

import com.hooklite.endshop.data.models.Shop;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class ShopTagType implements PersistentDataType<byte[], Shop> {
    private static final ShopTagType TAG_TYPE;

    static {
        TAG_TYPE = new ShopTagType();
    }

    public static ShopTagType getInstance() {
        return TAG_TYPE;
    }

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<Shop> getComplexType() {
        return Shop.class;
    }

    @Override
    public byte[] toPrimitive(Shop complex, PersistentDataAdapterContext context) {
        return Serializer.serialize(complex);
    }

    @Override
    public Shop fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        return (Shop) Serializer.deserialize(primitive);
    }
}
