package microservices.template.multiplication.helper;

import microservices.template.multiplication.autoconfig.ExecutorAutoConfigureRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ExecutorAutoConfigureRegistrar.class})
public @interface ProcessorComponentScan {
    @AliasFor("basePackages") String[] value() default {};

    @AliasFor("value") String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}
