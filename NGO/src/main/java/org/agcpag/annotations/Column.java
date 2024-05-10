package org.agcpag.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    public String name() default "";

    default String validateColumn(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("No name provided");
        }
        return name.toUpperCase();
    }
}
