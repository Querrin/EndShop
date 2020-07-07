package com.hooklite.endshop.data;

import com.hooklite.endshop.KeyType;
import com.hooklite.endshop.annotations.YamlKey;
import com.hooklite.endshop.annotations.YamlKeyType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.lang.reflect.Field;

public class YamlResolver {
    public static <E> E resolveValues(YamlConfiguration config, E object) throws IllegalAccessException {
        for(Field field : object.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(YamlKey.class)) {
                KeyType type = field.getAnnotation(YamlKeyType.class).value();

                if(type == KeyType.KEY)
                    field.set(field, config.getObject(field.getAnnotation(YamlKey.class).value(), field.getType()));
                else if(type == KeyType.KEY_LIST)
                    field.set(field, config.getKeys(false));
                else if(type == KeyType.KEY_LIST_DEEP)
                    field.set(field, config.getKeys(true));
            }
        }

        return object;
    }
}
