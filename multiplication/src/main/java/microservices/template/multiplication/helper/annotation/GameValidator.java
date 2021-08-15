package microservices.template.multiplication.helper.annotation;

import microservices.template.multiplication.processor.ABaseValidatorProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GameValidator {
    Class<? extends ABaseValidatorProcessor>[] value() default {};
}