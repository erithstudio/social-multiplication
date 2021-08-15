package microservices.template.multiplication.helper.annotation;

import org.springframework.stereotype.Service;
import org.thepavel.icomponent.Handler;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
@Service
@Handler("emailServiceMethodHandler")
public @interface EmailService {
}