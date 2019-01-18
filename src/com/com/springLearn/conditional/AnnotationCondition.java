package com.springLearn.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;

import java.util.Map;

public class AnnotationCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MethodMetadataReadingVisitor mm = (MethodMetadataReadingVisitor)metadata;
        Map<String, Object> attributes = mm.getAnnotationAttributes(ConditionAnnotation.class.getName());//
        String type = (String) attributes.get("value");
        return "annoConditionTest".equals(type);
    }
}
