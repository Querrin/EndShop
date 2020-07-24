package com.hooklite.endshop.data;

import com.hooklite.endshop.annotations.YamlKey;
import org.bukkit.configuration.file.YamlConfiguration;

import java.lang.reflect.Field;

public class YamlResolver {

    /**
     * Resolves the values from a config object using the paths provided in the YamlKey annotation then injects them into their fields.
     *
     * @param config The configuration file that the values will be pulled from.
     * @param object The object that the values will be injected to.
     * @throws IllegalAccessException If those fields are not accessible by the resolver.
     */
    public static <E> E resolveValues(YamlConfiguration config, E object) throws IllegalAccessException {
        for(Field field : object.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(YamlKey.class)) {
                field.set(field, config.getObject(field.getAnnotation(YamlKey.class).value(), field.getType()));
            }
        }

        return object;
    }

    /**
     * Resolves the values from a config object by prefixing the YamlKey path with the prefixPath then injects them into their fields.
     *
     * @param config     The configuration file that the values will be pulled from.
     * @param object     The object that the values will be injected to.
     * @param prefixPath The path that will be prefixed before the YamlKey path.
     * @throws IllegalAccessException If those fields are not accessible by the resolver.
     */
    public static <E> E resolveValues(YamlConfiguration config, E object, String prefixPath) throws IllegalAccessException {
        for(Field field : object.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(YamlKey.class)) {
                field.set(field, config.getObject(prefixPath + "." + field.getAnnotation(YamlKey.class).value(), field.getType()));
            }
        }

        return object;
    }

    public static <E> E toYamlConfig(YamlConfiguration config, E object) throws IllegalAccessException {
        for(Field field : object.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(YamlKey.class)) {
                config.set(field.getAnnotation(YamlKey.class).value(), );
            }
        }

        return object;
    }
}
