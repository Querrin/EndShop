package com.hooklite.endshop.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface YamlKey {
    String value() default "";
}
