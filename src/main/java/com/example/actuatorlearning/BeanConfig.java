package com.example.actuatorlearning;


import org.springframework.context.annotation.*;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;
import java.util.Map;

@Configuration
public class BeanConfig {

    private final static class DemoCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // style one:
            MergedAnnotations annotations = metadata.getAnnotations();
            MergedAnnotation<MyAnnotation> annotation = annotations.get(MyAnnotation.class);
            System.out.println(annotation.getString("value"));

            // style two:
            //MyAnnotation.class.getName(): com.example.actuatorlearning.BeanConfig$MyAnnotation
            Map<String, Object> myAnnotation = metadata.getAnnotationAttributes(MyAnnotation.class.getName());
            System.out.println(myAnnotation.get("value"));
            return false;
        }
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface MyAnnotation {
          String value();
     }

    @Conditional(DemoCondition.class)
    @MyAnnotation("my flag key")
    @Bean
    public MyEndpoint.Student student(){
        return new MyEndpoint.Student();
    }


}
