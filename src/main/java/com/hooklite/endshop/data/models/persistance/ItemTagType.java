package com.hooklite.endshop.data.models.persistance;

import com.hooklite.endshop.data.models.Item;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class ItemTagType implements PersistentDataType<Item, Item> {
    private static final ItemTagType TAG_TYPE;

    static {
        TAG_TYPE = new ItemTagType();
    }

    public static ItemTagType getInstance() {
        return TAG_TYPE;
    }

    @Override
    public Class<Item> getPrimitiveType() {
        return Item.class;
    }

    @Override
    public Class<Item> getComplexType() {
        return Item.class;
    }

    @Override
    public Item toPrimitive(Item complex, PersistentDataAdapterContext context) {
        return null;
    }

    @Override
    public Item fromPrimitive(Item primitive, PersistentDataAdapterContext context) {
        return null;
    }
}
