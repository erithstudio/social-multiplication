package microservices.template.multiplication.helper.annotation;

import microservices.template.multiplication.mapper.ABaseMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GameMapper {
    Class<? extends ABaseMapper>[] value() default {};
}