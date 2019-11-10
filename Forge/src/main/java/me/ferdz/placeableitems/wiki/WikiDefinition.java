package me.ferdz.placeableitems.wiki;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WikiDefinition {
    String name() default "";
    String description() default "";
    String model() default "";
    Texture[] textures() default { };

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Texture {
        String name() default "";
        String texture() default "";
        boolean hasMcMeta() default false;
    }
}
