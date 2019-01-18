package com.springLearn.springAnoEnableRelate.ImportSelector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomImportSelector.class)
public @interface EnableLog {
    String value() default "";
}
