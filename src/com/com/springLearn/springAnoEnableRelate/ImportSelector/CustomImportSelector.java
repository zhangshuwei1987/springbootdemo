package com.springLearn.springAnoEnableRelate.ImportSelector;


import com.springLearn.springAnoEnableRelate.Import.ImportClassConfig;
import com.springLearn.springAnoEnableRelate.Import.People;
import com.springLearn.springAnoEnableRelate.Import.Robbet;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
/**
 * selectImports方法的返回值，必须是一个class（全称），该class会被spring容器所托管起来
 */
public class CustomImportSelector implements ImportSelector {
    /*
    * importingClassMetadata:被注解的类或方法的元信息
    * */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        StandardAnnotationMetadata metadata = (StandardAnnotationMetadata) importingClassMetadata;
        //获取元类型或方法中所有注解的属性信息
        metadata.getAnnotationTypes().stream().forEach(entry ->{

        });
        //获取类中指定注解的参数值
        System.out.println(metadata.getAllAnnotationAttributes(EnableLog.class.getName()));
        //这里可以获取到注解的详细信息，然后根据信息去动态的返回需要被spring容器管理的bean
        return new String[]{People.class.getName(), Robbet.class.getName(), ImportClassConfig.class.getName()};

    }
}
