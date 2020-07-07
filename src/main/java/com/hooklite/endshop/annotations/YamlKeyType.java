package com.hooklite.endshop.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface YamlKeyType {
    com.hooklite.endshop.KeyType value() default com.hooklite.endshop.KeyType.KEY;
}
