package com.springLearn.conditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;

/*
* 自定义Bean加载条件,继承Condition复写matchs
* */
public class TestTypeCondition implements Condition {
    private static Logger LOGGER = LoggerFactory.getLogger(TestTypeCondition.class);
    /*
    * AnnotatedTypeMetadata(interface) : Method or Type annotated by@Conditional
    * impl:MethodMetadataReadingVisitor
    * */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        MethodMetadataReadingVisitor mm = (MethodMetadataReadingVisitor)metadata;
        LOGGER.info("mm.getMethodName()",mm.getMethodName());
        LOGGER.info("mm.getDeclaringClassName()",mm.getDeclaringClassName());

        return "conditionTest".equalsIgnoreCase(mm.getMethodName());

    }
}
